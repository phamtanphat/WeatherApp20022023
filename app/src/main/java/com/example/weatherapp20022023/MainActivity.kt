package com.example.weatherapp20022023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp20022023.data.remote.RetrofitClient
import com.example.weatherapp20022023.data.repository.WeatherRepository
import com.example.weatherapp20022023.presentation.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private var repository: WeatherRepository = WeatherRepository()
    private var mainViewModel: MainViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(repository) as T
            }
        })[MainViewModel::class.java]

        mainViewModel?.getLiveDataWeatherTempCity()?.observe(this) {
            Log.d("BBB", it.toString())
        }

        mainViewModel?.searchTempCity("Hanoi")
    }
}
