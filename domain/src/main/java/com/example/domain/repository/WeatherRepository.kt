package com.example.domain.repository

import com.example.domain.model.weather.Weather

interface WeatherRepository {
    suspend fun getWeather(
        dataType : String,
        numOfRows : Int,
        pageNo : Int,
        baseData : String,
        baseTime : String,
        nx : Int,
        ny : Int
    ) : Weather
}