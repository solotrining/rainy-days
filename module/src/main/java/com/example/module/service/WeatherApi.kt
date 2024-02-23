package com.example.module.service

import com.example.module.data.response.WeatherRes
import com.example.module.key.ApiKey.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("getVilageFcst?serviceKey=$API_KEY")
    suspend fun getWeather(
        @Query("dataType") dataType : String,
        @Query("numOfRows") numOfRows : Int,
        @Query("pageNo") pageNo : Int,
        @Query("base_date") baseDate : Int,
        @Query("base_time") baseTime : Int,
        @Query("nx") nx : String,
        @Query("ny") ny : String
    ) : WeatherRes
}