package com.example.weatherviewapi.Domains

import java.time.format.DateTimeFormatter
import java.util.Date

data class Data (
    val date: Date,
    val hour: DateTimeFormatter,
    val humidity: Int,
    val temperature: Int
)