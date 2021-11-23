package com.example.weatherapp.interactors

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.Cities
import com.example.weatherapp.data_class.WeatherAPIResponse
import com.example.weatherapp.network.ApiInterface
import com.example.weatherapp.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherInfoShowModelImpl : WeatherInfoShowModel {
    override fun getCityNames(callback: RequestCompleteListener<MutableList<String>>) {
        callback.onRequestSuccess(Cities.citiesList)
    }

    override fun getWeatherInfo(
        cityName: String,
        callback: RequestCompleteListener<WeatherAPIResponse>
    ) {

        val apiInterface:ApiInterface = RetrofitClient.client.create(ApiInterface::class.java)
        val call: Call<WeatherAPIResponse> = apiInterface.callApiForWeatherInfo(cityName, BuildConfig.APP_ID)

        call.enqueue(object: Callback<WeatherAPIResponse>{
            override fun onResponse(
                call: Call<WeatherAPIResponse>,
                response: Response<WeatherAPIResponse>
            ) {
                if(response.body() != null)
                {
                    callback.onRequestSuccess(response.body()!!)
                }
                else
                {
                    callback.onRequestFailed(response.message())
                }
            }

            override fun onFailure(call: Call<WeatherAPIResponse>, t: Throwable) {
               callback.onRequestFailed(t.localizedMessage)
            }

        })

    }
}