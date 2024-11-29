package com.example.weatherviewapi.Adapter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class SafeDoubleAdapter : JsonDeserializer<Double> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Double {
        return try {
            val value = json.asString
            if (value.equals("NaN", ignoreCase = true)) {
                0.0 // Defina um valor padrão para "NaN"
            } else {
                value.toDouble()
            }
        } catch (e: Exception) {
            0.0 // Valor padrão caso a conversão falhe
        }
    }
}
