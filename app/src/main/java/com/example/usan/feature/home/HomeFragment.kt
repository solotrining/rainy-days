package com.example.usan.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.usan.base.context.BaseFragment
import com.example.usan.base.viewmodel.repeatOnStarted
import com.example.usan.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collect

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {
    override val fragmentViewModel: HomeViewModel by viewModels()

    override fun observeAndInitViewModel() = binding{
        viewModel = fragmentViewModel.apply {
            repeatOnStarted { eventFlow.collect { handleEvent(it) } }
        }
    }

    override fun afterBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
    }

    private fun handleEvent(event: HomeViewModel.HomeEvent) = when(event){
        is HomeViewModel.HomeEvent.ClockClick -> {}
    }

}