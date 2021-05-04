package com.capgemini.demo.weatherapp.api_service

import com.capgemini.demo.weatherapp.datamodel.ApiResponseModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiRequest {

//    http://api.worldweatheronline.com/key=83d0dc2e87a04c098d8114101210305&q=pune&format=json
    @GET("premium/v1/search.ashx?")
    fun search(@Query("key") key : String,@Query("q") queryText : String,@Query("format") format : String, ): Single<ApiResponseModel?>?
}