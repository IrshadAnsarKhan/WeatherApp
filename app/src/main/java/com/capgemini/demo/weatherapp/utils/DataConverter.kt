package com.capgemini.demo.weatherapp.utils

import com.capgemini.demo.weatherapp.datamodel.ApiResponseModel
import com.capgemini.demo.weatherapp.datamodel.Result
import com.capgemini.demo.weatherapp.db.model.WeatherRoomDataModel

class DataConverter {
    fun convertToSearchModelList(apiResponseData: ApiResponseModel?): ArrayList<Result>? {
        /*val citiesData = ArrayList<String>()
        if (apiResponseData != null && apiResponseData.search_api != null && apiResponseData.search_api.result != null) {
            val data = apiResponseData.search_api.result;
            for (result in data) {
                citiesData.add(result.areaName[0].value + ", " + result.country[0].value)
            }
        }
        return citiesData;*/
        if (apiResponseData != null && apiResponseData.search_api != null && apiResponseData.search_api.result != null) {
            return apiResponseData.search_api.result as ArrayList
        }

        return ArrayList()
    }

    fun convertToDbModel(result: Result): WeatherRoomDataModel {
        val id: String = result.areaName[0].value + "-" + result.country[0].value

        return WeatherRoomDataModel(
            id,
            result.areaName[0].value,
            result.country[0].value,
            result.region[0].value,
            result.latitude.toString(),
            result.longitude.toString(),
            result.population.toString(),
            result.weatherUrl[0].value,
            System.currentTimeMillis()
        )
    }

}