package com.capgemini.demo.weatherapp.db.room

import com.capgemini.demo.weatherapp.db.model.WeatherRoomDataModel

interface DatabaseHelper {

    suspend fun fetchSearchData(): List<WeatherRoomDataModel>

    suspend fun insert(weatherRoomDataModel: WeatherRoomDataModel)

}