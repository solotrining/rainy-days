package com.example.usan.feature.main

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
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

    override fun observeAndInitViewModel() = binding { viewModel = activityViewModel.apply {} }

    override fun afterBinding() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController
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