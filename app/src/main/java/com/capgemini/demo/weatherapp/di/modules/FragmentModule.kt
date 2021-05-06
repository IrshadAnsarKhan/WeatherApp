package com.capgemini.demo.weatherapp.di.modules

import com.capgemini.demo.weatherapp.details.DetailsFragment
import com.capgemini.demo.weatherapp.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @ContributesAndroidInjector
    fun homeFragment(): HomeFragment?

    @ContributesAndroidInjector
    fun detailsFragment(): DetailsFragment?
}