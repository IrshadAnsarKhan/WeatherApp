package com.capgemini.demo.weatherapp.details.viewmodel

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.capgemini.demo.weatherapp.datamodel.Result

class DetailsViewModel() : ViewModel() {
    fun getData(arguments: Bundle): Result {
        return arguments.getSerializable("selectedCity") as Result
    }

    fun getGeoLocationText(result: Result?): CharSequence? {
        return result?.latitude.toString() + "," + result?.longitude.toString()
    }
}