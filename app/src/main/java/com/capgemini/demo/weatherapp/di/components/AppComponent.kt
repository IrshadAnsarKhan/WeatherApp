package com.capgemini.demo.weatherapp.di.components

import com.capgemini.demo.weatherapp.di.AppModule
import com.capgemini.demo.weatherapp.di.WeatherApplication
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : AndroidInjector<WeatherApplication?> {
    @Component.Factory
    interface Factory : AndroidInjector.Factory<WeatherApplication?>
}