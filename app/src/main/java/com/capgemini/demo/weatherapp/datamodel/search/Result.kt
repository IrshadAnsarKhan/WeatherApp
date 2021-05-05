package com.capgemini.demo.weatherapp.datamodel.search

import com.google.gson.annotations.SerializedName

data class Result (
    @SerializedName("areaName") val areaName : List<AreaName>,
    @SerializedName("country") val country : List<Country>,
    @SerializedName("region") val region : List<Region>,
    @SerializedName("latitude") val latitude : Double,
    @SerializedName("longitude") val longitude : Double,
    @SerializedName("population") val population : Int,
    @SerializedName("weatherUrl") val weatherUrl : List<WeatherUrl>
) : java.io.Serializable