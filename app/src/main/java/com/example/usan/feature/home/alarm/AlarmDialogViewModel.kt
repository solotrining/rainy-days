package com.example.usan.feature.home.alarm

import androidx.lifecycle.viewModelScope
import com.example.usan.base.viewmodel.BaseViewModel
import com.example.usan.base.viewmodel.MutableEventFlow
import com.example.usan.base.viewmodel.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlarmDialogViewModel @Inject constructor() : BaseViewModel() {

    private val _eventFlow = MutableEventFlow<AlarmEvent>()
    val eventFlow
        get() = _eventFlow.asEventFlow()

    sealed class AlarmEvent {
        data class ClickButton(val unit : Unit? = null) : AlarmEvent()
    }

    private fun event(event : AlarmEvent) = viewModelScope.launch { _eventFlow.emit(event) }

    fun clickButton() = event(AlarmEvent.ClickButton())

}