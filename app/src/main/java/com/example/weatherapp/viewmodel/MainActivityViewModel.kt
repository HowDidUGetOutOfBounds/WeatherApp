package com.example.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.CITY_KEY
import com.example.weatherapp.THEME_KEY
import com.example.weatherapp.interactors.RequestCompleteListener
import com.example.weatherapp.interactors.WeatherInfoShowModelImpl
import com.example.weatherapp.storage.SharedPreferencesStorage

class MainActivityViewModel(private val model: WeatherInfoShowModelImpl, private val sharedPrefStorage: SharedPreferencesStorage) : ViewModel() {

    val cityListLiveData = MutableLiveData<MutableList<String>>()
    val cityListFailureLiveData = MutableLiveData<String>()
    val progressBarLiveData = MutableLiveData<Boolean>()

    fun getCityList() {
        model.getCityNames(object : RequestCompleteListener<MutableList<String>>{
            override fun onRequestSuccess(data: MutableList<String>) {
                cityListLiveData.postValue(data)
            }

            override fun onRequestFailed(errorMessage: String) {
                // check if we load from JSON.
            }

        })
    }

    fun saveChosenCity(cityId: Int) {
        sharedPrefStorage.saveData(CITY_KEY, cityListLiveData.value!![cityId])
        Log.d("tag", "saveChosenCity: ")
    }

    fun loadCityFromCache(): Int {
        val saved = sharedPrefStorage.getData(CITY_KEY)
        var resultId : Int? = 0

        if(saved != "")
        {
           resultId = cityListLiveData.value?.indexOf(saved)

            if((resultId == -1) || (resultId == null))
            {
                resultId = 0
            }
        }

        Log.d("tag", "$resultId ")

        return resultId!!
    }

    fun saveThemeState(boolean: Boolean)
    {
        sharedPrefStorage.saveBool(THEME_KEY, boolean)
    }

    fun loadThemeState(): Boolean
    {
        return  sharedPrefStorage.getBool(THEME_KEY)
    }
}