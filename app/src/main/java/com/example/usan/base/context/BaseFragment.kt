package com.example.usan.base.context

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.usan.base.viewmodel.BaseViewModel

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment <T : ViewDataBinding, V : BaseViewModel>(private val inflate: Inflate<T>) : Fragment() {

    private var _binding: T? = null
    val binding get() = _binding!!

    protected abstract val fragmentViewModel: V

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater,container,false)
        binding.lifecycleOwner = this
        afterBinding(inflater, container, savedInstanceState)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeAndInitViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun observeAndInitViewModel()
    abstract fun afterBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
    protected fun binding(action: T.() -> Unit) { binding.run(action) }

    protected fun navigateToFrag(act: NavDirections) {
        findNavController().run {
            currentDestination?.getAction(act.actionId)?.run {
                navigate(act)
            }
        }
    }

    protected fun popUpBackStack() = findNavController().popBackStack()

}