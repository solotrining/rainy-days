package com.example.module.di

import com.example.domain.repository.WeatherRepository
import com.example.domain.usecase.weather.GetWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun providesGetWeatherUseCase(repository: WeatherRepository) : GetWeatherUseCase = GetWeatherUseCase(repository)
}