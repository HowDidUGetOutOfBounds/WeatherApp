package com.example.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.*
import com.example.weatherapp.data_class.WeatherAPIResponse
import com.example.weatherapp.data_class.WeatherData
import com.example.weatherapp.interactors.RequestCompleteListener
import com.example.weatherapp.interactors.WeatherInfoShowModelImpl
import com.example.weatherapp.storage.SharedPreferencesStorage
import kotlinx.coroutines.launch

class MainActivityViewModel(private val model: WeatherInfoShowModelImpl, private val sharedPrefStorage: SharedPreferencesStorage) : ViewModel() {

    val cityListLiveData = MutableLiveData<MutableList<String>>()
    val progressBarLiveData = MutableLiveData<Boolean>()
    val weatherInfoFailureLiveData = MutableLiveData<String>()
    val weatherInfoLiveData = MutableLiveData<WeatherData>()

    fun getCityList() {
//        model.getCityNames(object : RequestCompleteListener<MutableList<String>>{
//            override fun onRequestSuccess(data: MutableList<String>) {
//                cityListLiveData.postValue(data)
//            }
//
//            override fun onRequestFailed(errorMessage: String) {
//                // check if we load from JSON.
//            }
//
//        })
        cityListLiveData.postValue(Cities.citiesList)
    }

    fun getWeatherInfo(){

        viewModelScope.launch {
            progressBarLiveData.postValue(true)
            val res: UiState = model.getWeatherInfo(cityListLiveData.value!![loadCityFromCache()])

            when(res)
            {
                is UI_Error -> {
                    weatherInfoFailureLiveData.postValue(res.reason)
                }
                is UI_Success -> {
                    val weatherData = WeatherData(
                        dateTime = res.response.dt.unixTimestampToDateTimeString(),
                        temperature = res.response.main.temp.kelvinToCelsius().toString(),
                        cityAndCountry = "${res.response.name}, ${res.response.sys.country}",
                        weatherConditionIconUrl = "http://openweathermap.org/img/w/${res.response.weather[0].icon}.png",
                        weatherConditionIconDescription = res.response.weather[0].description,
                        humidity = "${res.response.main.humidity}%",
                        pressure = "${res.response.main.pressure} mBar",
                        visibility = "${res.response.visibility/1000.0} KM",
                        sunrise = res.response.sys.sunrise.unixTimestampToTimeString(),
                        sunset = res.response.sys.sunset.unixTimestampToTimeString()
                    )
                    weatherInfoLiveData.postValue(weatherData)
                }
            }

            progressBarLiveData.postValue(false)
        }

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