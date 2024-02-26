package com.example.module.source.weather

import com.example.domain.model.weather.Weather
import com.example.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteSource: WeatherRemoteSource
) : WeatherRepository {
    override suspend fun getWeather(
        dataType: String,
        numOfRows: Int,
        pageNo: Int,
        baseData: String,
        baseTime: String,
        nx: Int,
        ny: Int
    ): Weather = remoteSource.getWeather(dataType, numOfRows, pageNo, baseData, baseTime, nx, ny)
}