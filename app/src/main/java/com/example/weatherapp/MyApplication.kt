package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.interactors.WeatherInfoShowModelImpl
import com.example.weatherapp.storage.SharedPreferencesStorage

class MyApplication : Application() {

    val sharedPreferencesStorage by lazy {
        SharedPreferencesStorage(this)
    }
    val weatherInfoShowModelImpl by lazy {
        WeatherInfoShowModelImpl()
    }

}