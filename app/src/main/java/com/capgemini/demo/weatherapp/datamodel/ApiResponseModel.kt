package com.capgemini.demo.weatherapp.datamodel

import com.google.gson.annotations.SerializedName

data class ApiResponseModel (
	@SerializedName("search_api") val search_api : Search_api
)