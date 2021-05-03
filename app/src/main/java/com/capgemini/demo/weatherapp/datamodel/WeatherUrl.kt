package com.capgemini.demo.weatherapp.datamodel

import com.google.gson.annotations.SerializedName

data class WeatherUrl (
	@SerializedName("value") val value : String
)