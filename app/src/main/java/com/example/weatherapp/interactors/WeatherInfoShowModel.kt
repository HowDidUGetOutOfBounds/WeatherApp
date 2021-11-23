package com.example.weatherapp.interactors

import com.example.weatherapp.data_class.WeatherAPIResponse

interface WeatherInfoShowModel {
    fun getCityNames(callback: RequestCompleteListener<MutableList<String>>) // TODO add loading from json
    fun getWeatherInfo(cityName: String, callback: RequestCompleteListener<WeatherAPIResponse>)
}