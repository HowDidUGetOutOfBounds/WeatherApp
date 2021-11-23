package com.example.weatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.R
import com.example.weatherapp.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
    }
}