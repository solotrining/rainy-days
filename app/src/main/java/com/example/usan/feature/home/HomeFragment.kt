package com.example.usan.feature.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.example.domain.constants.Constants.HOME_FRAGMENT
import com.example.usan.base.context.BaseFragment
import com.example.usan.base.viewmodel.repeatOnStarted
import com.example.usan.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate) {

    override val fragmentViewModel: HomeViewModel by viewModels()

    private lateinit var locationManager : LocationManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

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
        val lastLocation = if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) { return }
        else locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        Log.e("lastLocation", "${lastLocation}")

        if (fragmentViewModel.getTime() != "") binding.time = fragmentViewModel.getTime()
        else binding.time = "6"

        if (lastLocation != null) fragmentViewModel.getWeather(lastLocation.latitude.toInt(), lastLocation.longitude.toInt())
        else fragmentViewModel.getWeather(60, 127)

        setFragmentResultListener(HOME_FRAGMENT) { key, result ->
            val data = result.getInt(key)
            binding.time = "$data : 00"
            fragmentViewModel.setTime(data.toString())
        }
    }

    private fun handleEvent(event: HomeViewModel.HomeEvent) = when(event){
        is HomeViewModel.HomeEvent.ClockClick -> { navigateToFrag(HomeFragmentDirections.actionGlobalAlarmDialog()) }
    }

}