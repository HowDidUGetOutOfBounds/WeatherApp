package com.example.weatherapp.interactors

import com.example.weatherapp.data_class.WeatherAPIResponse
import com.example.weatherapp.viewmodel.UiState

interface WeatherInfoShowModel {
    fun getCityNames(callback: RequestCompleteListener<MutableList<String>>) // TODO add loading from json
    suspend fun getWeatherInfo(cityName: String) : UiState
}