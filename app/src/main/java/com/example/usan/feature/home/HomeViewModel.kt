package com.example.usan.feature.home

import android.util.Log
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.weather.GetWeatherUseCase
import com.example.usan.base.viewmodel.BaseViewModel
import com.example.usan.base.viewmodel.MutableEventFlow
import com.example.usan.base.viewmodel.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : BaseViewModel() {

    private val _eventFlow = MutableEventFlow<HomeEvent>()
    val eventFlow
        get() = _eventFlow.asEventFlow()

    private val _rainPercent = MutableStateFlow("계산중...")
    val rainPercent
        get() = _rainPercent.asLiveData()

    private val _umbrellaVisible = MutableStateFlow(true)
    val umbrellaVisible
        get() = _umbrellaVisible.asLiveData()

    private val _takeUmbrellaText = MutableStateFlow("우산을 챙겨 가시는 걸 추천 해요!")
    val takeUmbrellaText
        get() = _takeUmbrellaText.asLiveData()

    init {
        getWeather()
    }

    sealed class HomeEvent {
        data class ClockClick(val unit : Unit? = null) : HomeEvent()
    }

    private fun getWeather() {
        getWeatherUseCase.invoke(
            dataType = "JSON",
            numOfRows = 1000,
            1,
            "20240226",
            "0500",
            55,
            127,
            scope = viewModelScope,
            onResult = {
                it.response.body.items.item.forEach { item ->
                    if (item.category == "POP") {
                        emitRainPercent(item.fcstValue.toInt())
                        setUmbrella(item.fcstValue.toInt())
                    }
                }
            },
            onFailure = {
                Log.e("error", it?.message.toString())
            }
        )
        // 0 ~ 5 맑
    }

    private fun setUmbrella(pop : Int) = viewModelScope.launch {
        _umbrellaVisible.emit(pop > 50)
        setTakeUmbrellaText()
    }

    private fun setTakeUmbrellaText() = viewModelScope.launch {
        if (_umbrellaVisible.value) _takeUmbrellaText.emit("우산을 챙겨 가시는 걸 추천 해요!")
        else _takeUmbrellaText.emit("우산을 챙겨 가지 않아도 괜찮아요!")
    }

    private fun emitRainPercent(percent : Int) = viewModelScope.launch { _rainPercent.emit("$percent%") }

    private fun event(event : HomeEvent) = viewModelScope.launch { _eventFlow.emit(event) }

    fun clickClock() = event(HomeEvent.ClockClick())

}