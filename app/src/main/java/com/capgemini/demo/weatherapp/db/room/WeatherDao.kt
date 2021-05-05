package com.capgemini.demo.weatherapp.db.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.capgemini.demo.weatherapp.db.model.WeatherRoomDataModel

@Dao
interface WeatherDao {

    @Query("SELECT * FROM WeatherTable ORDER BY timestamp DESC LIMIT 10")
    suspend fun getAll(): List<WeatherRoomDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(roomDataModel: WeatherRoomDataModel)
}