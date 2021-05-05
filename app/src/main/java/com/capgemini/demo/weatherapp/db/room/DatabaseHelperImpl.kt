package com.capgemini.demo.weatherapp.db.room

import androidx.lifecycle.LiveData
import com.capgemini.demo.weatherapp.db.model.WeatherRoomDataModel

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun fetchSearchData(): List<WeatherRoomDataModel> = appDatabase.weatherDao().getAll()

    override suspend fun insert(weatherRoomDataModel: WeatherRoomDataModel) = appDatabase.weatherDao().insert(weatherRoomDataModel)

}