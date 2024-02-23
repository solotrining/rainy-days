package com.example.module.source.weather

import com.example.module.data.response.WeatherRes
import com.example.module.service.WeatherApi
import javax.inject.Inject

interface WeatherRemoteSource {
    suspend fun getWeather(
        dataType : String,
        numOfRows : Int,
        pageNo : Int,
        baseData : Int,
        baseTime : Int,
        nx : String,
        ny : String)
    : WeatherRes
}

class WeatherRemoteSourceImpl @Inject constructor(
    private val api : WeatherApi
) : WeatherRemoteSource {
    override suspend fun getWeather(
        dataType : String,
        numOfRows : Int,
        pageNo : Int,
        baseData : Int,
        baseTime : Int,
        nx : String,
        ny : String
    ): WeatherRes = api.getWeather(dataType, numOfRows, pageNo, baseData, baseTime, nx, ny)

}