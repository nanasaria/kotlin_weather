package com.example.weatherviewapi.Network

import com.example.weatherviewapi.Domains.Data
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface DataService {
    private val client = OkHttpClient()

    suspend fun buscar(date: String, hour:String): response{
        val request = request.builder()
            .url("http://localhost:3004/buscar?date=$date&hour=$hour")
            .build()

        return withContext(Dispatchers.IO) {
            client.newCall(request).execute()
        }
    }
    suspend fun addWeather(humidity: String, temperature: String): response{
        val request = request.builder()
            .url("http://localhost:3004/addWeather?humidity=$humidity&temperature=$temperature")
            .build()

        return withContext(Dispatchers.IO) {
            client.newCall(request).execute()
        }
    }

    suspend fun addWeather(humidity: String, temperature: String): response{
        val request = request.builder()
            .url("http://localhost:3004/addWeather?humidity=$humidity&temperature=$temperature")
            .build()

        return withContext(Dispatchers.IO) {
            client.newCall(request).execute()
        }
    }

    suspend fun listWeather(): Response {
        val request = Request.Builder()
            .url("http://seu-servidor:porta/listWeather") // Substitua pela URL correta
            .build()

        return withContext(Dispatchers.IO) {
            client.newCall(request).execute()
        }
    }

}
