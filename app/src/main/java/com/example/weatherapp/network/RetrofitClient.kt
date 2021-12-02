package com.example.weatherapp.network

import com.example.weatherapp.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

/**
 * very cool singleton of retrofit
 * @author MrHowDidYouGetOutOfBounds
 */

object RetrofitClient {

    private var retrofit: Retrofit? = null
    private val gson = GsonBuilder().create()

    val client: Retrofit
        get() {
            if(retrofit == null)
            {
                synchronized(Retrofit::class.java)
                {
                    if(retrofit == null)
                    {
                        val httpClient = OkHttpClient.Builder()

                        val interceptor = HttpLoggingInterceptor()
                        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

                        val client = httpClient.addInterceptor(interceptor).build()

                        retrofit = Retrofit.Builder()
                            .baseUrl(BuildConfig.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .client(client)
                            .build()

                    }
                }
            }
            return  retrofit!!
        }

}