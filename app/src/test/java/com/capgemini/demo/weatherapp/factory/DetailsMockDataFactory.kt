package com.capgemini.demo.weatherapp.factory

import com.capgemini.demo.weatherapp.datamodel.city_wheather.*
import com.capgemini.demo.weatherapp.datamodel.search.*

class DetailsMockDataFactory {

    companion object {

        fun getWeatherApiResponseModel(): WeatherApiResponseModel {
            val request: ArrayList<Request> = ArrayList()
            request.add(getRequest())

            val currentCondition: ArrayList<Current_condition> = ArrayList()
            currentCondition.add(getCurrentCondition())

            val data = Data(request, currentCondition)
            return WeatherApiResponseModel(data);
        }

        private fun getRequest(): Request {
            return Request("city", "Pune, India");
        }

        fun getCurrentCondition(): Current_condition {
            val weatherDesc: ArrayList<WeatherDesc> = ArrayList()
            weatherDesc.add(WeatherDesc("Cloudy"))

            val weatherIconUrl: ArrayList<WeatherIconUrl> = ArrayList()
            weatherIconUrl.add(WeatherIconUrl("https://Cloudy.url"))

            return Current_condition(
                temp_C = 37,
                temp_F = 37,
                weatherDesc = weatherDesc,
                weatherIconUrl = weatherIconUrl,
                humidity = 60
            );
        }


    }
}