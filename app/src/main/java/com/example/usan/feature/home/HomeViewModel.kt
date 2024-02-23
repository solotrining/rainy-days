package com.example.usan.feature.home

import androidx.lifecycle.viewModelScope
import com.example.usan.base.viewmodel.BaseViewModel
import com.example.usan.base.viewmodel.MutableEventFlow
import com.example.usan.base.viewmodel.asEventFlow
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    private val _eventFlow = MutableEventFlow<HomeEvent>()
    val eventFlow
        get() = _eventFlow.asEventFlow()


    sealed class HomeEvent {
        data class ClockClick(val unit : Unit? = null) : HomeEvent()
    }

    private fun event(event : HomeEvent) = viewModelScope.launch { _eventFlow.emit(event) }

    fun clickClock() = event(HomeEvent.ClockClick())

}