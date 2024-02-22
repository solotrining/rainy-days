package com.example.usan.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    private val _baseViewEvent = MutableEventFlow<BaseEvent>()
    val baseViewEvent
        get() = _baseViewEvent

    sealed class BaseEvent {
        data class BackClick(val unit : Unit? = null): BaseEvent()
    }

    protected fun baseEvent(event: BaseEvent) = viewModelScope.launch { _baseViewEvent.emit(event) }

    open fun backClick() = baseEvent(BaseEvent.BackClick())
}