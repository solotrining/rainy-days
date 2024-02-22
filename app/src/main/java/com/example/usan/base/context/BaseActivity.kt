package com.example.usan.base.context

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.example.usan.base.viewmodel.BaseViewModel

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel>(
    val bindingFactory: (LayoutInflater) -> T
) : AppCompatActivity()  {

    protected lateinit var binding: T
        private set

    protected abstract val activityViewModel: V

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = bindingFactory(layoutInflater)
        binding.lifecycleOwner = this

        afterBinding()
        observeAndInitViewModel()
    }

    abstract fun observeAndInitViewModel()
    abstract fun afterBinding()

    protected fun binding(action: T.() -> Unit) {
        binding.run(action)
    }

}