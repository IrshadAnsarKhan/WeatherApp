package com.capgemini.demo.weatherapp.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capgemini.demo.weatherapp.base.BaseApiResponseModel
import com.capgemini.demo.weatherapp.datamodel.ApiResponseModel
import com.capgemini.demo.weatherapp.datamodel.Search_api

class HomeViewModel : ViewModel() {

    private val cityMutableLiveData: MutableLiveData<BaseApiResponseModel<ApiResponseModel>> =
        MutableLiveData()

    fun getCityMutableLiveData(): MutableLiveData<BaseApiResponseModel<ApiResponseModel>> {
        return cityMutableLiveData;
    }

    fun searchProductsResponseLiveData(searchQuery: String) {

    }
}