package com.example.weatherapp20022023.data.repository

import com.example.weatherapp20022023.common.AppConstant
import com.example.weatherapp20022023.data.model.WeatherTempCity
import com.example.weatherapp20022023.data.remote.RetrofitClient

/**
 * Created by pphat on 6/5/2023.
 */
class WeatherRepository {
    private var apiService = RetrofitClient.getApiService()

    suspend fun searchTempByCityName(cityName: String): WeatherTempCity {
        return apiService.callTempFromCityName(AppConstant.APP_ID, AppConstant.UNITS, cityName)
    }
}
