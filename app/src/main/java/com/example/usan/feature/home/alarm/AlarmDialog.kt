package com.example.usan.feature.home.alarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.example.domain.constants.Constants.HOME_FRAGMENT
import com.example.usan.base.context.BaseDialog
import com.example.usan.base.viewmodel.repeatOnStarted
import com.example.usan.databinding.AlarmDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlarmDialog : BaseDialog<AlarmDialogBinding, AlarmDialogViewModel>(AlarmDialogBinding::inflate) {
    override val dialogViewModel: AlarmDialogViewModel by viewModels()

    override fun afterBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding {
        numberPicker.maxValue = 24
        numberPicker.minValue = 1
        numberPicker.value = 1
    }

    override fun observeAndInitViewModel() = binding {
        viewModel = dialogViewModel.apply {
            repeatOnStarted { eventFlow.collect { handleEvent(it) } }
        }
    }

    private fun handleEvent(event : AlarmDialogViewModel.AlarmEvent) = when(event) {
        is AlarmDialogViewModel.AlarmEvent.ClickButton -> {
            popUpBackStack()
            setFragmentResult(HOME_FRAGMENT, bundleOf(HOME_FRAGMENT to binding.numberPicker.value))
        }
    }

    override fun onBackPressed() {
        popUpBackStack()
    }

}