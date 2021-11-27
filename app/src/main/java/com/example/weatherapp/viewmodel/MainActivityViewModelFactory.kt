package com.example.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.MyApplication

/**
 * That factory give me opportunity to have 1 instance of VM which survives orientation changes and
 * which is shared between all fragments that are related to my activity. That's kinda cool
 * @author MrHowDidYouGetOutOfBounds
 */
class MainActivityViewModelFactory(private val application: MyApplication) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(application.weatherInfoShowModelImpl) as T
    }
}