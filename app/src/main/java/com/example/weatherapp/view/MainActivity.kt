package com.example.weatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.MyApplication
import com.example.weatherapp.R
import com.example.weatherapp.setTheme
import com.example.weatherapp.viewmodel.MainActivityViewModel
import com.example.weatherapp.viewmodel.MainActivityViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        mainActivityViewModel = ViewModelProvider(
            this,
            MainActivityViewModelFactory(application as MyApplication)
        ).get(MainActivityViewModel::class.java)


        setTheme(mainActivityViewModel.loadThemeState())
    }
}