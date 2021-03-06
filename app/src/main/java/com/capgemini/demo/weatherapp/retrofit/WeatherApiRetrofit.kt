package com.capgemini.demo.weatherapp.retrofit

import android.content.Context
import com.capgemini.demo.weatherapp.BuildConfig
import com.capgemini.demo.weatherapp.base.BaseRetrofit
import retrofit2.Retrofit
import javax.inject.Inject

class WeatherApiRetrofit @Inject constructor(private val context: Context) : BaseRetrofit() {

    private lateinit var retrofit: Retrofit

    fun getRetrofitInstance(): Retrofit {
        retrofit =
            getRetrofitInstance(context, BuildConfig.BASE_URL)
        return retrofit
    }
}