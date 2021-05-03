package com.capgemini.demo.weatherapp.datamodel

import com.google.gson.annotations.SerializedName

data class Search_api (

	@SerializedName("result") val result : List<Result>
)