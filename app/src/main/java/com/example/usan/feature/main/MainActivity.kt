package com.example.usan.feature.main

import android.os.Bundle
import androidx.activity.viewModels
import com.example.usan.base.context.BaseActivity
import com.example.usan.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>({ActivityMainBinding.inflate(it)}) {
    override val activityViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun observeAndInitViewModel() = binding {
        viewModel = activityViewModel.apply {

        }
    }

    override fun afterBinding() {
    }

    fun navigate() {

    }
}