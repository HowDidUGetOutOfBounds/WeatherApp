package com.example.weatherapp

import androidx.appcompat.app.AppCompatDelegate

object Cities{
    val citiesList = mutableListOf<String>(
        "Minsk", // 1
        "Grodno", //2
        "Vitebsk", //3
        "Mogilev", //4
        "Brest", //5
        "Vitebsk", //6
        "Gomel", //7
        "Moscow", //8
        "Vilnuis", //9
        "Kyiv", //10
        "Warsaw", //11
        "Riga" //12
    )
}

fun setTheme(ifNight: Boolean) {
    if(ifNight) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
    else
    {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}

val CITY_KEY : String = "SH_P_City"
val THEME_KEY : String = "SH_P_Theme"
