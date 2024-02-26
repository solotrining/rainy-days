package com.example.usan.base.binding

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("visible")
    fun setVisible(view : View, visibility : Boolean){
        view.visibility = if(visibility) View.VISIBLE else View.INVISIBLE
    }

}