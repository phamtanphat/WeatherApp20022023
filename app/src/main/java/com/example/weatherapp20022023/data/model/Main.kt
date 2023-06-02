package com.example.weatherapp20022023.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Main (
    val temp: Double,
    @SerializedName("temp_min")
    @Expose
    val tempMin: Double,
    @SerializedName("temp_max")
    @Expose
    val tempMax: Double,
    val humidity: Long
)
