package com.example.module.data.response

import com.example.domain.model.weather.Weather
import com.example.module.mapper.WeatherMapper
import com.google.gson.annotations.SerializedName

data class WeatherRes(
    @SerializedName("response")
    val _response : Response
) : Weather {

    override val response: Weather.Response
        get() = WeatherMapper.WeatherToWeather(this)

    data class Response(
        val header: Header,
        val body: Body
    )

    data class Header(
        val resultCode: Int,
        val resultMsg: String
    )

    data class Body(
        val dataType: String,
        val items: Items
    )

    data class Items(
        val item: List<Item>
    )

    data class Item(
        val baseData: Int,
        val baseTime: Int,
        val category: String,
        val fcstDate : Int,
        val fcstTime : Int,
        val fcstValue : String,
        val nx : Int,
        val ny : Int
    )
}