package com.example.weatherapp.viewmodel

import com.example.weatherapp.data_class.WeatherAPIResponse

sealed class UiState
data class UI_Error(val reason : String) : UiState()
data class UI_Success(val response: WeatherAPIResponse) : UiState()