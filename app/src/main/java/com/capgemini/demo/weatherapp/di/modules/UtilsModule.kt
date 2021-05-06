package com.capgemini.demo.weatherapp.di.modules

import com.capgemini.demo.weatherapp.retrofit.WeatherApiRetrofit
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class UtilsModule {

    @Provides
    fun provideRetrofit(weatherApiRetrofit: WeatherApiRetrofit): Retrofit {
        return weatherApiRetrofit.getRetrofitInstance()
    }
}