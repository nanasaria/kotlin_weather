package com.example.weatherviewapi.Adapter

import com.google.gson.*
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

class DateTypeAdapter : JsonDeserializer<Date>, JsonSerializer<Date> {

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))

    override fun serialize(src: Date?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(dateFormat.format(src))
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Date {
        val dateStr = json?.asString ?: throw JsonParseException("Date is null or invalid")
        return try {
            dateFormat.parse(dateStr) ?: throw JsonParseException("Invalid date format")
        } catch (e: Exception) {
            throw JsonParseException("Unable to parse date: $dateStr", e)
        }
    }
}
