package com.capgemini.demo.weatherapp.base

data class BaseApiResponseModel<T>(
    val isSuccessful: Boolean = false,
    val apiException: String? = null,
    var apiResponseData: T? = null
)
