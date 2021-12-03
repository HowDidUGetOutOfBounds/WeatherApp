package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.di.AppComponent
import com.example.weatherapp.di.DaggerAppComponent
import com.example.weatherapp.interactors.WeatherInfoShowModelImpl
import com.example.weatherapp.storage.SharedPreferencesStorage

class MyApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

//    val sharedPreferencesStorage by lazy {
//        SharedPreferencesStorage(this)
//    }
//    val weatherInfoShowModelImpl by lazy {
//        WeatherInfoShowModelImpl()
//    }

}