package com.capgemini.demo.weatherapp.datamodel.city_wheather

import com.google.gson.annotations.SerializedName

data class WeatherApiResponseModel(
    @SerializedName("data") val data: Data
)