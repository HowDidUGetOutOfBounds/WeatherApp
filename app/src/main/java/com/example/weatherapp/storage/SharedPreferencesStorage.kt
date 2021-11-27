package com.example.weatherapp.storage

import android.content.Context

class SharedPreferencesStorage(context: Context): Storage {

    private val sharedPreferences = context.getSharedPreferences("appStorage", Context.MODE_PRIVATE)

    override fun saveCity(key: String, value: String) {
        with(sharedPreferences.edit())
        {
            putString(key, value)
        }
    }

    override fun getCity(key: String): String {
        return sharedPreferences.getString(key, "")!!
    }
}