package com.capgemini.demo.weatherapp.di.modules

import android.content.Context
import com.capgemini.demo.weatherapp.db.room.DatabaseBuilder
import com.capgemini.demo.weatherapp.db.room.DatabaseHelperImpl
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideDBHelper(context : Context): DatabaseHelperImpl {
        return DatabaseHelperImpl(DatabaseBuilder.getInstance(context))
    }
}