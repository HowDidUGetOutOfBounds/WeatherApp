package com.example.weatherapp.network

import com.example.weatherapp.data_class.WeatherAPIResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("weather")
    suspend fun callApiForWeatherInfo(@Query("q") cityName: String, @Query("appid") myAppId: String): WeatherAPIResponse
}