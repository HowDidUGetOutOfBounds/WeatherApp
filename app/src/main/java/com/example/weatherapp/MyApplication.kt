package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.interactors.WeatherInfoShowModelImpl

class MyApplication : Application() {

    val weatherInfoShowModelImpl by lazy {
        WeatherInfoShowModelImpl()
    }

}