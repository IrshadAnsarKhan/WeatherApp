package com.capgemini.demo.weatherapp.home

import com.capgemini.demo.weatherapp.BuildConfig
import com.capgemini.demo.weatherapp.api_service.WeatherApiRequest
import com.capgemini.demo.weatherapp.constants.AppConstants
import com.capgemini.demo.weatherapp.datamodel.city_wheather.WeatherApiResponseModel
import com.capgemini.demo.weatherapp.datamodel.search.SearchApiResponseModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiRequest: WeatherApiRequest) {

    fun search(query: String): Single<SearchApiResponseModel?>? {
        return apiRequest.search(BuildConfig.API_KEY, query, AppConstants.API_RESPONSE_FORMAT_JSON)
    }

    fun weather(query: String): Single<WeatherApiResponseModel?>? {
        return apiRequest.weather(BuildConfig.API_KEY, query, AppConstants.API_RESPONSE_FORMAT_JSON)
    }
}