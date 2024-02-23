package com.example.module.di

import com.example.module.source.weather.WeatherRemoteSource
import com.example.module.source.weather.WeatherRemoteSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun providesWeatherRemoteSource(source: WeatherRemoteSourceImpl) : WeatherRemoteSource = source

}