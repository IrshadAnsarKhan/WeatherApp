package com.capgemini.demo.weatherapp.datamodel.city_wheather

import com.google.gson.annotations.SerializedName

data class WeatherIconUrl (

	@SerializedName("value") val value : String
)