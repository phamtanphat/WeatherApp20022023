package com.example.weatherapp20022023.data.remote

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by pphat on 5/31/2023.
 */
object RetrofitClient {
    private var instance: Retrofit = createRetrofit()
    private var apiService = instance.create(ApiService::class.java)

    fun getApiService(): ApiService = apiService

    private fun createRetrofit(): Retrofit {

        val okhttpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

        val gson = GsonBuilder().create()

        return Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/")
            .client(okhttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}
