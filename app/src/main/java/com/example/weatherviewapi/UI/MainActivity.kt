package com.example.weatherviewapi.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherviewapi.Adapter.DataAdapter
import com.example.weatherviewapi.Domains.Data
import com.example.weatherviewapi.Domains.ResponseData
import com.example.weatherviewapi.R
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var textDateTime: TextView
    private lateinit var recyclreView: RecyclerView
    private val client = OkHttpClient()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textDateTime = findViewById(R.id.textView3)
        recyclreView = findViewById(R.id.viewHistoric)
        recyclreView.layoutManager = LinearLayoutManager(this)
        val dataList = ArrayList<ResponseData>()

        val data = Data(Date(), Date())

        lifecycleScope.launch {
            try {
                val response = getData(data)
                response?.let {
                    dataList.add(it)
                    recyclreView.adapter = DataAdapter(dataList)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
        val hourFormatter = SimpleDateFormat("HH:mm:ss", Locale("pt", "BR"))

        val currentDate = Date()
        val formattedDate = dateFormatter.format(currentDate)
        val formattedHour = hourFormatter.format(currentDate)
        textDateTime.text = "$formattedDate | $formattedHour"
    }


    suspend fun getData(data: Data): ResponseData? {
        val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
        val hourFormatter = SimpleDateFormat("HH:mm:ss", Locale("pt", "BR"))

        val formattedDate = dateFormatter.format(data.date)
        val formattedHour = hourFormatter.format(data.hour)

        val formBody = FormBody.Builder()
            .add("date", formattedDate)
            .add("hour", formattedHour)
            .build()

        val request = Request.Builder()
            .url("http://localhost:3004/buscar")
            .post(formBody)
            .build()

        return withContext(Dispatchers.IO) {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                val responseBody = response.body?.string() ?: return@withContext null
                val gson = Gson()
                gson.fromJson(responseBody, ResponseData::class.java)
            }
        }
    }

}

