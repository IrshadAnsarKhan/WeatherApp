package com.capgemini.demo.weatherapp.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capgemini.demo.weatherapp.db.room.DatabaseHelper
import com.capgemini.demo.weatherapp.home.ApiRepository
import com.capgemini.demo.weatherapp.utils.DataConverter

class DetailViewModelFactory(
    private val apiRepository: ApiRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsViewModel(apiRepository) as T
    }
}