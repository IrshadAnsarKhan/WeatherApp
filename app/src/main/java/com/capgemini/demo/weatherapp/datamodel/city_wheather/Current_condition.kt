package com.capgemini.demo.weatherapp.datamodel.city_wheather

import com.google.gson.annotations.SerializedName

data class Current_condition (
	@SerializedName("observation_time") val observation_time : String = "",
	@SerializedName("temp_C") val temp_C : Int,
	@SerializedName("temp_F") val temp_F : Int,
	@SerializedName("weatherCode") val weatherCode : Int = 0,
	@SerializedName("weatherIconUrl") val weatherIconUrl : List<WeatherIconUrl>,
	@SerializedName("weatherDesc") val weatherDesc : List<WeatherDesc>,
	@SerializedName("windspeedMiles") val windspeedMiles : Int = 0,
	@SerializedName("windspeedKmph") val windspeedKmph : Int  = 0,
	@SerializedName("winddirDegree") val winddirDegree : Int  = 0,
	@SerializedName("winddir16Point") val winddir16Point : String = "",
	@SerializedName("precipMM") val precipMM : Double = 0.0,
	@SerializedName("precipInches") val precipInches : Double = 0.0,
	@SerializedName("humidity") val humidity : Int,
	@SerializedName("visibility") val visibility : Int = 0,
	@SerializedName("visibilityMiles") val visibilityMiles : Int = 0,
	@SerializedName("pressure") val pressure : Int = 0,
	@SerializedName("pressureInches") val pressureInches : Int = 0,
	@SerializedName("cloudcover") val cloudcover : Int = 0,
	@SerializedName("FeelsLikeC") val feelsLikeC : Int = 0,
	@SerializedName("FeelsLikeF") val feelsLikeF : Int = 0,
	@SerializedName("uvIndex") val uvIndex : Int =0
)