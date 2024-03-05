package com.example.usan.base.context

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.usan.base.viewmodel.BaseViewModel

abstract class BaseDialog <T : ViewDataBinding, V : BaseViewModel>(private val inflate: Inflate<T>)
    : DialogFragment() {


    private var _binding : T? = null
    val binding
        get() = _binding!!

    protected abstract val dialogViewModel: V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        binding.lifecycleOwner = this
        isCancelable = true
        afterBinding(inflater, container, savedInstanceState)
        return binding.root
    }

    abstract fun afterBinding(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { observeAndInitViewModel() }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun binding(action: T.() -> Unit) { binding.run(action) }

    abstract fun observeAndInitViewModel()
    abstract fun onBackPressed()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : Dialog(requireActivity()) {
            @Deprecated("Deprecated in Java", ReplaceWith("dismiss()"))
            override fun onBackPressed() {
                this@BaseDialog.onBackPressed()
            }
        }
    }

    protected fun popUpBackStack() = findNavController().popBackStack()

}