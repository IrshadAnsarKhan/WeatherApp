package com.capgemini.demo.weatherapp.factory

import com.capgemini.demo.weatherapp.datamodel.search.*
import com.capgemini.demo.weatherapp.db.model.WeatherRoomDataModel

class MockDataFactory {

    companion object {

        fun getSearchApiResponseModel(): SearchApiResponseModel {
            val results: ArrayList<Result> = ArrayList()
            results.add(getResultModel())
            val searchApi = Search_api(results)
            return SearchApiResponseModel(searchApi);
        }

        fun getWeatherRoomDataModel(): WeatherRoomDataModel {
            val responseModel = WeatherRoomDataModel(
                "area-region-country", "area", "country",
                "region", "11.11", "22.22", "10000", "https://test.url", 1000L
            )
            return responseModel;
        }

        fun getWeatherRoomDataModel(id: String): WeatherRoomDataModel {
            val responseModel = WeatherRoomDataModel(
                id, "area", "country",
                "region", "11.11", "22.22", "10000", "https://test.url", 1000L
            )
            return responseModel;
        }

        fun getResultModel(): Result {
            val area = ArrayList<AreaName>()
            area.add(AreaName("area"))

            val country = ArrayList<Country>()
            country.add(Country("country"))

            val region = ArrayList<Region>()
            region.add(Region("region"))

            val weatherUrl = ArrayList<WeatherUrl>()
            weatherUrl.add(WeatherUrl("https://test.url"))

            return Result(area, country, region, 11.11, 22.22, 10000, weatherUrl);
        }


    }
}