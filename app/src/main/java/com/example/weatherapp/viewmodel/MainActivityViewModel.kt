package com.example.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.weatherapp.interactors.RequestCompleteListener
import com.example.weatherapp.interactors.WeatherInfoShowModelImpl

class MainActivityViewModel(private val model: WeatherInfoShowModelImpl) : ViewModel() {

    fun getCityList() {
        model.getCityNames(object : RequestCompleteListener<MutableList<String>>{
            override fun onRequestSuccess(data: MutableList<String>) {

            }

            override fun onRequestFailed(errorMessage: String) {
                // check if we load from JSON.
            }

        })
    }
}