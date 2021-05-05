package com.capgemini.demo.weatherapp.datamodel.search

import com.google.gson.annotations.SerializedName

data class WeatherUrl (
	@SerializedName("value") val value : String
)