package com.capgemini.demo.weatherapp.converter

import com.capgemini.demo.weatherapp.factory.MockDataFactory
import com.capgemini.demo.weatherapp.utils.DataConverter
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DataConverterTest {

    @MockK(relaxUnitFun = true)
    lateinit var dataConverter: DataConverter

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        dataConverter = DataConverter()
    }

    @Test
    fun `test convert to DB Model`() {

        val actualWeatherRoomDataModel = MockDataFactory.getWeatherRoomDataModel()
        val result = MockDataFactory.getResultModel()
        val expectedWeatherModel = dataConverter.convertToDbModel(result)

        Assertions.assertEquals(expectedWeatherModel.id, actualWeatherRoomDataModel.id)
        Assertions.assertEquals(expectedWeatherModel.areaName, actualWeatherRoomDataModel.areaName)
        Assertions.assertEquals(expectedWeatherModel.country, actualWeatherRoomDataModel.country)
        Assertions.assertEquals(expectedWeatherModel.region, actualWeatherRoomDataModel.region)
        Assertions.assertEquals(expectedWeatherModel.latitude, actualWeatherRoomDataModel.latitude)
        Assertions.assertEquals(
            expectedWeatherModel.longitude,
            actualWeatherRoomDataModel.longitude
        )
        Assertions.assertEquals(
            expectedWeatherModel.weatherUrl,
            actualWeatherRoomDataModel.weatherUrl
        )
    }

    @Test
    fun `test prepare database id from result`() {
        val expectedID = "area-region-country"
        val result = MockDataFactory.getResultModel()
        val actualId = dataConverter.prepareDatabaseIdFromResult(result)

        Assertions.assertEquals(expectedID, actualId)
    }

    @Test
    fun `test prepare display id from result`() {
        val expectedID = "area, region, country"
        val result = MockDataFactory.getResultModel()
        val actualId = dataConverter.prepareDisplayIdFromResult(result)

        Assertions.assertEquals(expectedID, actualId)
    }
}