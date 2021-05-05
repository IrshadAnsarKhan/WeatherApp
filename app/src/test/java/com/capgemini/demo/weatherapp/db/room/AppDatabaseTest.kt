package com.capgemini.demo.weatherapp.db.room

import android.content.Context
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.capgemini.demo.weatherapp.factory.MockDataFactory
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest : TestCase() {

    private lateinit var weatherDao: WeatherDao
    private lateinit var appDatabase: AppDatabase

    @Before
    public override fun setUp() {
        val context = mock(Context::class.java)
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        weatherDao = appDatabase.weatherDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    fun `test DB insertSearchData and fetchSearchData queries`(): Unit = runBlocking {
        val weatherRoomDataModel = MockDataFactory.getWeatherRoomDataModel()
        weatherDao.insert(weatherRoomDataModel)
        val weatherRoomDataModels = weatherDao.fetchSearchData()
        Assertions.assertThat(weatherRoomDataModels.contains(weatherRoomDataModel)).isTrue
    }

}