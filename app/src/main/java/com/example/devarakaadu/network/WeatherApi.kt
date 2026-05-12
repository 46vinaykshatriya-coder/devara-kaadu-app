package com.example.devarakaadu.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

data class WeatherResponse(

    val main: Main,

    val weather: List<Weather>
)

data class Main(

    val temp: Double,

    val humidity: Int
)

data class Weather(

    val main: String,

    val description: String
)

interface WeatherApi {

    @GET("data/2.5/weather")

    suspend fun getWeather(

        @Query("lat")
        lat: Double,

        @Query("lon")
        lon: Double,

        @Query("appid")
        apiKey: String,

        @Query("units")
        units: String = "metric"
    ): Response<WeatherResponse>
}