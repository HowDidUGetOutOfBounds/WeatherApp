package com.example.weatherapp.interactors

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.Cities
import com.example.weatherapp.data_class.WeatherAPIResponse
import com.example.weatherapp.network.ApiInterface
import com.example.weatherapp.network.RetrofitClient
import com.example.weatherapp.viewmodel.UI_Error
import com.example.weatherapp.viewmodel.UI_Success
import com.example.weatherapp.viewmodel.UiState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherInfoShowModelImpl : WeatherInfoShowModel {
    override fun getCityNames(callback: RequestCompleteListener<MutableList<String>>) {
        callback.onRequestSuccess(Cities.citiesList)
    }

    override suspend fun getWeatherInfo(
        cityName: String,
    ): UiState {

        val apiInterface:ApiInterface = RetrofitClient.client.create(ApiInterface::class.java)

        try {
            val response: WeatherAPIResponse =
                apiInterface.callApiForWeatherInfo(cityName, BuildConfig.APP_ID)
            return UI_Success(response)
        }
        catch (cause: Throwable)
        {
            return UI_Error("Something went wrong with network call" + cause.localizedMessage)
        }


    }
}