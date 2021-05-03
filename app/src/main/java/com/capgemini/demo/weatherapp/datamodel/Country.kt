package com.capgemini.demo.weatherapp.datamodel

import com.google.gson.annotations.SerializedName

data class Country (

	@SerializedName("value") val value : String
)