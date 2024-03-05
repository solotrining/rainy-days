package com.example.usan.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.module.local.PreferenceManager
import kotlinx.coroutines.launch
import javax.inject.Inject

open class BaseViewModel : ViewModel() {

    @Inject
    lateinit var preferencesManager: PreferenceManager

    private val _baseViewEvent = MutableEventFlow<BaseEvent>()
    val baseViewEvent
        get() = _baseViewEvent

    sealed class BaseEvent {
        data class BackClick(val unit : Unit? = null): BaseEvent()
    }

    protected fun baseEvent(event: BaseEvent) = viewModelScope.launch { _baseViewEvent.emit(event) }

    open fun backClick() = baseEvent(BaseEvent.BackClick())
}