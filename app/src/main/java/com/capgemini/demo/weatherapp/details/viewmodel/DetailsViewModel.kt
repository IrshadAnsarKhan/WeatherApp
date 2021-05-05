package com.capgemini.demo.weatherapp.details.viewmodel

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.capgemini.demo.weatherapp.datamodel.Result
import com.capgemini.demo.weatherapp.db.model.WeatherRoomDataModel

class DetailsViewModel() : ViewModel() {
    fun getData(arguments: Bundle): WeatherRoomDataModel {
        return arguments.getSerializable("selectedCity") as WeatherRoomDataModel
    }

    fun getGeoLocationText(roomDataModel: WeatherRoomDataModel?): CharSequence? {
        return roomDataModel?.latitude.toString() + "," + roomDataModel?.longitude.toString()
    }
}