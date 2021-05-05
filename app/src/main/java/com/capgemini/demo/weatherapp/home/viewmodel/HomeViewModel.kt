package com.capgemini.demo.weatherapp.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capgemini.demo.weatherapp.base.BaseApiResponseModel
import com.capgemini.demo.weatherapp.datamodel.ApiResponseModel
import com.capgemini.demo.weatherapp.datamodel.Result
import com.capgemini.demo.weatherapp.db.model.WeatherRoomDataModel
import com.capgemini.demo.weatherapp.db.room.DatabaseHelper
import com.capgemini.demo.weatherapp.home.ApiRepository
import com.capgemini.demo.weatherapp.utils.DataConverter
import com.capgemini.demo.weatherapp.utils.LiveDataUtils
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.launch

class HomeViewModel(
    private val apiRepository: ApiRepository,
    private val dbHelper: DatabaseHelper,
    private val dataConverter: DataConverter,
) : ViewModel() {

    private val cityMutableLiveData: MutableLiveData<BaseApiResponseModel<ApiResponseModel>> =
        MutableLiveData()

    fun getCityMutableLiveData(): MutableLiveData<BaseApiResponseModel<ApiResponseModel>> {
        return cityMutableLiveData;
    }

    private val dbDataMutableLiveData: MutableLiveData<List<WeatherRoomDataModel>> =
        MutableLiveData()

    fun getDbDataMutableLiveData(): MutableLiveData<List<WeatherRoomDataModel>> {
        return dbDataMutableLiveData;
    }

    fun searchProductsResponseLiveData(searchQuery: String) {
        val baseApiResponseModelSingle: Single<ApiResponseModel?>? =
            apiRepository.search(searchQuery);
        LiveDataUtils.updateStatus(cityMutableLiveData, baseApiResponseModelSingle)
    }

    fun fetchSearchedData() {
        viewModelScope.launch {
            try {
                val usersFromDb = dbHelper.fetchSearchData()
                dbDataMutableLiveData.postValue(usersFromDb)
                // here you have your usersFromDb

            } catch (e: Exception) {
                // handler error
                e.printStackTrace()
            }
        }
    }

    fun convertToDbModel(result: Result): WeatherRoomDataModel {
        return dataConverter.convertToDbModel(result)
    }

    fun insertData(weatherRoomDataModel: WeatherRoomDataModel) {
        viewModelScope.launch {
            try {
                dbHelper.insert(weatherRoomDataModel)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}