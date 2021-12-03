package com.example.weatherapp.di

import android.content.Context
import com.example.weatherapp.view.SettingsMainFragment
import com.example.weatherapp.view.WeatherMainInfoFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: WeatherMainInfoFragment)
    fun inject(fragment: SettingsMainFragment)
}