package br.com.cvaccari.orders.commons.extensions

import java.text.NumberFormat
import java.util.*

fun Double.toCurrency(): String {
    val ptBr = Locale("pt", "BR")
    var value = NumberFormat.getCurrencyInstance(ptBr).format(this)
    return value.toString()
}