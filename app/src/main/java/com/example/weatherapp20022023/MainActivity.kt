package com.example.weatherapp20022023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.weatherapp20022023.data.model.WeatherTempCity
import com.example.weatherapp20022023.data.remote.RetrofitClient
import com.example.weatherapp20022023.data.repository.WeatherRepository
import com.example.weatherapp20022023.presentation.viewmodel.MainViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    private var editTextCityName: EditText? = null
    private var tvCityName: TextView? = null
    private var tvCountryName: TextView? = null
    private var tvTemp: TextView? = null
    private var tvStatus: TextView? = null
    private var tvHumidity: TextView? = null
    private var tvCloud: TextView? = null
    private var tvWind: TextView? = null
    private var tvCurrentDay: TextView? = null
    private var imgStatus: ImageView? = null
    private var btnNavigateScreen7Day: Button? = null
    private var btnSearch: Button? = null

    private var repository: WeatherRepository = WeatherRepository()
    private var mainViewModel: MainViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(repository) as T
            }
        })[MainViewModel::class.java]

        editTextCityName = findViewById(R.id.edit_text_input_city)
        tvCityName = findViewById(R.id.text_view_city_name)
        tvCountryName = findViewById(R.id.text_view_country_name)
        tvTemp = findViewById(R.id.text_view_temp)
        tvStatus = findViewById(R.id.text_view_status)
        tvHumidity = findViewById(R.id.text_view_humidity)
        tvCloud = findViewById(R.id.text_view_cloud)
        tvWind = findViewById(R.id.text_view_wind)
        tvCurrentDay = findViewById(R.id.text_view_current_day)
        imgStatus = findViewById(R.id.image_view_status)
        btnNavigateScreen7Day = findViewById(R.id.button_navigate_screen)
        btnSearch = findViewById(R.id.button_ok)

        mainViewModel?.getLiveDataWeatherTempCity()?.observe(this) {
            displayTemp(it)
        }

        btnSearch?.setOnClickListener {
            val cityName = editTextCityName?.text.toString()
            mainViewModel?.searchTempCity(cityName)
        }
    }

    fun displayTemp(weatherTempCity: WeatherTempCity) {
        tvCityName?.text = "Tên thành phố ${weatherTempCity.name}"
        tvCountryName?.text = "Tên quốc gia ${weatherTempCity.sys.country}"
        tvTemp?.text = weatherTempCity.main.temp.toString()
        tvStatus?.text = weatherTempCity.weather.getOrNull(0)?.main
        tvHumidity?.text = weatherTempCity.main.humidity.toString()
        tvCloud?.text = weatherTempCity.clouds.all.toString()
        tvWind?.text = weatherTempCity.wind.speed.toString()

        // Current day
        val currentTime = DateFormat.getTimeInstance().format(weatherTempCity.timeCurrent)
        tvCurrentDay?.text = currentTime

        imgStatus?.let {
            Glide
                .with(this)
                .load("http://openweathermap.org/img/w/" + weatherTempCity.weather.getOrNull(0)?.icon + ".png")
                .into(it)
        }
    }
}
