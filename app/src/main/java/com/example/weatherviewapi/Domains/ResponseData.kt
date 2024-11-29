package com.example.weatherviewapi.Domains

import com.example.weatherviewapi.Adapter.DateTypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.util.Date

data class ResponseData (
    @SerializedName("date")
    @JsonAdapter(DateTypeAdapter::class)
    val date: Date,
    val hour: String,
    val humidity: Double,
    val temperature: Double

)