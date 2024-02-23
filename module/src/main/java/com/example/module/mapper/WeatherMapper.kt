package com.example.module.mapper

import com.example.domain.model.weather.Weather
import com.example.module.data.response.WeatherRes

object WeatherMapper {

    fun WeatherToWeather(weatherRes: WeatherRes): Weather.Response {
        return Weather.Response(
            Weather.Header(weatherRes._response.header.resultCode, weatherRes._response.header.resultMsg),
            Weather.Body(
                dataType = weatherRes._response.body.dataType,
                items = Weather.Items(
                    item = item(weatherRes._response.body.items)
                )
            )
        )
    }

    private fun item(weatherRes: WeatherRes.Items): List<Weather.Item> {
        val list = ArrayList<Weather.Item>()

        weatherRes.item.forEach {
            list.add(
                Weather.Item(
                    it.baseData,
                    it.baseTime,
                    it.category,
                    it.fcstDate,
                    it.fcstTime,
                    it.fcstValue,
                    it.nx,
                    it.ny
                )
            )
        }
        return list.toList()
    }
}