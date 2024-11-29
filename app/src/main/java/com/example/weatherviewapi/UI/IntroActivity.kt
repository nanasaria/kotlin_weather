package com.example.weatherviewapi.UI

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherviewapi.Adapter.DataAdapter
import com.example.weatherviewapi.Adapter.DateTypeAdapter
import com.example.weatherviewapi.Domains.Data
import com.example.weatherviewapi.Domains.ResponseData
import com.example.weatherviewapi.Domains.WsResponseData
import com.example.weatherviewapi.R
import com.example.weatherviewapi.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.IOException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class IntroActivity : AppCompatActivity() {
    private val client = OkHttpClient()
    private lateinit var adapter: DataAdapter
    private val dataList = mutableListOf<ResponseData>()
    private lateinit var binding: ActivityMainBinding
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
    val hourFormatter = SimpleDateFormat("HH:mm:ss", Locale("pt", "BR"))
    val currentDate = Date()
    val formattedDate = dateFormatter.format(currentDate)
    val formattedHour = hourFormatter.format(currentDate)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = DataAdapter(
            context = this,
            dataList = dataList
        )

        binding.viewHistoric.adapter = adapter
        binding.textView3.text = "$formattedDate | $formattedHour"


        // Buscar dados da API
        val requestData = Data(formattedDate, formattedHour)
        lifecycleScope.launch {
            fetchData(requestData)
        }

    }

    private suspend fun fetchData(data: Data) {
        try {
            val response = getData(data)
            response?.let {
                dataList.clear()
                println("IT:  " + it)
                dataList.addAll(it)
                adapter.updateData(it) // Atualizar RecyclerView
                println("dataList:  " + dataList)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun getData(data: Data): List<ResponseData>? {
        val url = HttpUrl.Builder()
            .scheme("http")
            .host("10.0.2.2")  // Use '10.0.2.2' para localhost no emulador Android
            .port(3004)
            .addPathSegment("buscar")
            .addQueryParameter("date", data.date)
            .addQueryParameter("hour", data.hour)
            .build()

        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        return withContext(Dispatchers.IO) {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Código inesperado: $response")

                val responseBody = response.body?.string() ?: return@withContext null
                val gson = GsonBuilder()
                    .setDateFormat("dd/MM/yyyy HH:mm:ss")
                    .create()
                println("response Body: " + responseBody)

                // Deserializar para uma lista de ResponseData
                gson.fromJson(responseBody, Array<ResponseData>::class.java).toList()
            }
        }
    }

    inner class MyWebsocketListener : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            val jsonObject = JSONObject()
            jsonObject.put("date", formattedDate)
            jsonObject.put("hour", formattedHour)
            webSocket.send(jsonObject.toString())
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            runOnUiThread {
                handleIncomingMessage(text, webSocket)
            }

        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
            println("FALHA WEBSOCKET: ${t.message}")
        }

    }

    private fun handleIncomingMessage(message: String, webSocket: WebSocket){

        println("mensagem incomming message: ${message}")

        try {
            val gson = GsonBuilder()
                .setDateFormat("dd/MM/yyyy HH:mm:ss")
                .create()

            // Deserializar para uma lista de ResponseData
            //        val parsetWsResponse = gson.fromJson(message, Array<ResponseData>::class.java)
            val parsetWsResponse = gson.fromJson(message, WsResponseData::class.java)

            println("Acessando os valores: ")
            println(parsetWsResponse)
        }
        catch (e : Exception){
            println("ERRO PARSER: ${e.message}")
        }

        try {

            val requestData = Data(formattedDate, formattedHour)
            lifecycleScope.launch {
                fetchData(requestData)
            }

//                println("response Body websoccket: " + responseBody)
//        println("Acessando os valores: ")
//        println(parsetWsResponse)            // Output: update
//        println(parsetWsResponse.data.humidity)    // Output: 50.00
//        println(parsetWsResponse.data.temperature) // Output: 29.90
            //        //atualiza o recycler
            //        lifecycleScope.launch(Dispatchers.Main) {
            //            adapter.notifyItemInserted(dataList.size)
            //        }
//        }
    }catch (e: Exception){
        println("ERRO websocket request: ${e.message}")
    }

}
}
