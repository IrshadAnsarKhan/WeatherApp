package com.capgemini.demo.weatherapp.retrofit

import android.content.Context
import com.capgemini.demo.weatherapp.BuildConfig
import com.capgemini.demo.weatherapp.base.BaseRetrofit
import retrofit2.Retrofit

class WeatherApiRetrofit() : BaseRetrofit() {

    private lateinit var retrofit: Retrofit

    fun getRetrofitInstance(context: Context): Retrofit {
        retrofit =
            getRetrofitInstance(context, BuildConfig.BASE_URL)
        return retrofit
    }
}