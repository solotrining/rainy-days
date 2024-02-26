package com.example.module.di

import com.example.domain.repository.WeatherRepository
import com.example.module.source.weather.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesWeatherRepository(repository: WeatherRepositoryImpl) : WeatherRepository = repository
}