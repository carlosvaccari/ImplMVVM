package br.com.cvaccari.orders.commons.custombindings

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import br.com.cvaccari.orders.R


@BindingAdapter("priceTextColor")
fun TextView.priceTextColor(value: Double?) {
    setTextColor(
        when (value) {
            null -> ContextCompat.getColor(this.context, R.color.black)
            else -> ContextCompat.getColor(this.context, R.color.green)
        }
    )
}