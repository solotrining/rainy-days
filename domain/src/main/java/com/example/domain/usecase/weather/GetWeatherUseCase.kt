package com.example.domain.usecase.weather

import com.example.domain.model.weather.Weather
import com.example.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetWeatherUseCase (
    private val repository: WeatherRepository
) {

    operator fun invoke(
        dataType : String,
        numOfRows : Int,
        pageNo : Int,
        baseData : Int,
        baseTime : Int,
        nx : String,
        ny : String,
        scope: CoroutineScope,
        onResult: (Weather) -> Unit,
        onFailure : (Throwable?) -> Unit
    ) {
        scope.launch(Dispatchers.Main) {
            val result = runCatching { withContext(Dispatchers.IO) { repository.getWeather(dataType, numOfRows, pageNo, baseData, baseTime, nx, ny) } }
            result.onSuccess { weather -> onResult(weather) }
            result.onFailure { exception -> onFailure(exception) }
        }
    }
}