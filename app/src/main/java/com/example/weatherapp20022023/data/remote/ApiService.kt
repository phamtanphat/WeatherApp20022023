package com.example.weatherapp20022023.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by pphat on 5/31/2023.
 */
interface ApiService {

    //data/2.5/weather?appid=2206d84c8189efe365465e3318f487aa&units=metric&q=Ho Chi Minh
    @GET("data/2.5/weather")
    suspend fun callTempFromCityName(
        @Query("appid") appid: String,
        @Query("units") units: String,
        @Query("q") q: String,
    )
}
