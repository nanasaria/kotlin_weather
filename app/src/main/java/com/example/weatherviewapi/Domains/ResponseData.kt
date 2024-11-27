package com.example.weatherviewapi.Domains

import java.util.Date

data class ResponseData (
    val date: Date,
    val hour: Date,
    val humidity: Int,
    val temperature: Int

)