package com.capgemini.demo.weatherapp.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.capgemini.demo.weatherapp.db.model.WeatherRoomDataModel

@Database(entities = [WeatherRoomDataModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

}