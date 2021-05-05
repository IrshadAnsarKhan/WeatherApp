package com.capgemini.demo.weatherapp.utils

import com.capgemini.demo.weatherapp.datamodel.search.SearchApiResponseModel
import com.capgemini.demo.weatherapp.datamodel.search.Result
import com.capgemini.demo.weatherapp.db.model.WeatherRoomDataModel

class DataConverter {
    fun convertToSearchModelList(searchApiResponseData: SearchApiResponseModel?): ArrayList<Result>? {
        if (searchApiResponseData != null && searchApiResponseData.search_api != null && searchApiResponseData.search_api.result != null) {
            return searchApiResponseData.search_api.result as ArrayList
        }

        return ArrayList()
    }

    fun convertToDbModel(result: Result): WeatherRoomDataModel {

        return WeatherRoomDataModel(
            prepareDatabaseIdFromResult(result),
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

    fun prepareDatabaseIdFromResult(result: Result): String {
        return result.areaName[0].value + ", " + result.region[0].value
    }

    fun prepareDisplayIdFromResult(result: Result): String {
        return result.areaName[0].value + ", " + result.region[0].value + ", " +result.country[0].value
    }

}