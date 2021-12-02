package com.example.weatherapp

import androidx.appcompat.app.AppCompatDelegate
import java.text.SimpleDateFormat
import java.util.*

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

fun Long.unixTimestampToDateTimeString() : String {

    try {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this//*1000.toLong()

        val outputDateFormat = SimpleDateFormat("dd MMM, yyyy - hh:mm a", Locale.ENGLISH)
        outputDateFormat.timeZone = TimeZone.getDefault() // user's default time zone
        return outputDateFormat.format(calendar.time)

    } catch (e: Exception) {
        e.printStackTrace()
    }

    return this.toString()
}

fun Long.unixTimestampToTimeString() : String {

    try {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this//*1000.toLong()

        val outputDateFormat = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
        outputDateFormat.timeZone = TimeZone.getDefault()
        return outputDateFormat.format(calendar.time)

    } catch (e: Exception) {
        e.printStackTrace()
    }

    return this.toString()
}


/**
 * The temperature T in degrees Celsius (°C) is equal to the temperature T in Kelvin (K) minus 273.15:
 * T(°C) = T(K) - 273.15
 */
fun Double.kelvinToCelsius() : Int {

    return  (this - 273.15).toInt()
}

val CITY_KEY : String = "SH_P_City"
val THEME_KEY : String = "SH_P_Theme"
