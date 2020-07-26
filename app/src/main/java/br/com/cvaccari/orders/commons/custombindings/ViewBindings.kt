package br.com.cvaccari.orders.commons.custombindings

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.BindingAdapter

@BindingAdapter("showIf")
fun View.showIf(value: Boolean?) {
    visibility = if(value == true) {
        VISIBLE
    } else {
        GONE
    }
}