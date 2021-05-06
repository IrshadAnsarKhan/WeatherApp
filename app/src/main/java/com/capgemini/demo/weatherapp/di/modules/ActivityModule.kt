package com.capgemini.demo.weatherapp.di.modules

import com.capgemini.demo.weatherapp.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {
    @ContributesAndroidInjector
    fun mainActivity(): MainActivity?
}