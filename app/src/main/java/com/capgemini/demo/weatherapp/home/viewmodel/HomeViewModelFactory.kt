package com.capgemini.demo.weatherapp.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capgemini.demo.weatherapp.home.ApiRepository

class HomeViewModelFactory(private val apiRepository: ApiRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(apiRepository) as T
    }
}