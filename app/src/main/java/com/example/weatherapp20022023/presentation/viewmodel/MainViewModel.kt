package com.example.weatherapp20022023.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp20022023.data.model.WeatherTempCity
import com.example.weatherapp20022023.data.repository.WeatherRepository
import kotlinx.coroutines.*

/**
 * Created by pphat on 6/5/2023.
 */
class MainViewModel(
    private var weatherRepository: WeatherRepository
): ViewModel() {

    private var liveDataWeatherTempCity = MutableLiveData<WeatherTempCity>()

    fun getLiveDataWeatherTempCity(): LiveData<WeatherTempCity> = liveDataWeatherTempCity

    fun searchTempCity(cityName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val deffer = async { weatherRepository.searchTempByCityName(cityName) }
                val weatherTempCity = deffer.await()
                withContext(Dispatchers.Main) {
                    liveDataWeatherTempCity.value = weatherTempCity
                }
            } catch (e: Exception) {
                Log.d("BBB", e.toString())
            }
        }
    }
}
