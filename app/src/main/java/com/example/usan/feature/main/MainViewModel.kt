package com.example.usan.feature.main

import com.example.usan.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    fun getTime() = preferencesManager.getString("time")

}