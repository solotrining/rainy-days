package com.example.usan.feature.main

import android.Manifest
import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.example.usan.R
import com.example.usan.base.context.BaseActivity
import com.example.usan.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>({ActivityMainBinding.inflate(it)}) {

    override val activityViewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController


    @SuppressLint("InlinedApi")
    private val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
    )

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
            } else -> { Toast.makeText(this, "기본 위치가 서울로 고정됩니다.", Toast.LENGTH_SHORT) }

        }
    }


    override fun observeAndInitViewModel() = binding { viewModel = activityViewModel.apply {} }

    override fun afterBinding() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        permissionLauncher.launch(locationPermissions)
    }

    private fun navigate(directions: NavDirections) = navController.navigate(directions)

    override fun checkNetwork(): Boolean {
        val cm = getSystemService(ConnectivityManager::class.java)
        val networkCapabilities = cm.activeNetwork ?: return false
        val actNw = cm.getNetworkCapabilities(networkCapabilities) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}