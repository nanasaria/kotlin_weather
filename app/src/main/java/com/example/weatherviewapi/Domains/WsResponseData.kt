package com.example.weatherviewapi.Domains

data class WsResponseData(
    val event: String,
    val data: Data
) {
    data class Data(
        val humidity: String,
        val temperature: String
    )
}
// caso retorne double:
//data class WsResponseData(
//    val event: String,
//    val data: Data
//) {
//    data class Data(
//        val humidity: Double,
//        val temperature: Double
//    )
//}
