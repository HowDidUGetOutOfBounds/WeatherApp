package com.example.weatherapp.interactors

interface RequestCompleteListener<T> {
    fun onRequestSuccess(data: T)
    fun onRequestFailed(errorMessage: String)
}