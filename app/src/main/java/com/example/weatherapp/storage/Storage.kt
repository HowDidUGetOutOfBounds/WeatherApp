package com.example.weatherapp.storage

interface Storage {
    fun saveCity(key: String, value: String)
    fun getCity(key: String): String
}