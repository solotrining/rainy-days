package com.example.module.source.weather

import com.example.module.data.response.WeatherRes
import com.example.module.service.WeatherApi
import javax.inject.Inject

interface WeatherRemoteSource {
    suspend fun getWeather(
        dataType : String,
        numOfRows : Int,
        pageNo : Int,
        baseData : String,
        baseTime : String,
        nx : Int,
        ny : Int)
    : WeatherRes
}

class WeatherRemoteSourceImpl @Inject constructor(
    private val api : WeatherApi
) : WeatherRemoteSource {
    override suspend fun getWeather(
        dataType : String,
        numOfRows : Int,
        pageNo : Int,
        baseData : String,
        baseTime : String,
        nx : Int,
        ny : Int
    ): WeatherRes = api.getWeather(dataType, numOfRows, pageNo, baseData, baseTime, nx, ny)

}