package com.capgemini.demo.weatherapp.home

import com.capgemini.demo.weatherapp.BuildConfig
import com.capgemini.demo.weatherapp.api_service.WeatherApiRequest
import com.capgemini.demo.weatherapp.constants.AppConstants
import com.capgemini.demo.weatherapp.datamodel.ApiResponseModel
import io.reactivex.rxjava3.core.Single

class ApiRepository(private val apiRequest: WeatherApiRequest) {

    fun search(query: String): Single<ApiResponseModel?>? {
        return apiRequest.search(BuildConfig.API_KEY, query, AppConstants.API_RESPONSE_FORMAT_JSON)
    }
}