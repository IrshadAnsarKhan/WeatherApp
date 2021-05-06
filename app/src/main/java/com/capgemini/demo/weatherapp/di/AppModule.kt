package com.capgemini.demo.weatherapp.di

import android.app.Application
import android.content.Context
import com.capgemini.demo.weatherapp.di.modules.ActivityModule
import com.capgemini.demo.weatherapp.di.modules.DatabaseModule
import com.capgemini.demo.weatherapp.di.modules.FragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class, ActivityModule::class, FragmentModule::class, DatabaseModule::class])
abstract class AppModule {
    @Binds
    abstract fun application(app: WeatherApplication?): Application?
    @Binds
    abstract fun applicationContext(app: Application?): Context?
}