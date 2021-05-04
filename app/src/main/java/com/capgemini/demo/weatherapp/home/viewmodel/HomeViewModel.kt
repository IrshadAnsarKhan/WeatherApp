package com.capgemini.demo.weatherapp.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capgemini.demo.weatherapp.base.BaseApiResponseModel
import com.capgemini.demo.weatherapp.datamodel.ApiResponseModel
import com.capgemini.demo.weatherapp.home.ApiRepository
import com.capgemini.demo.weatherapp.utils.LiveDataUtils
import io.reactivex.rxjava3.core.Single

class HomeViewModel(private val apiRepository: ApiRepository) : ViewModel() {

    private val cityMutableLiveData: MutableLiveData<BaseApiResponseModel<ApiResponseModel>> =
        MutableLiveData()

    fun getCityMutableLiveData(): MutableLiveData<BaseApiResponseModel<ApiResponseModel>> {
        return cityMutableLiveData;
    }

    fun searchProductsResponseLiveData(searchQuery: String) {
        val baseApiResponseModelSingle: Single<ApiResponseModel?>? =
            apiRepository.search(searchQuery);
        LiveDataUtils.updateStatus(cityMutableLiveData, baseApiResponseModelSingle)
    }
}