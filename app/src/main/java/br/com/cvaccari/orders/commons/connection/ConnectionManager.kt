package br.com.cvaccari.orders.commons.connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class ConnectionManager(private val context: Context) {

    fun isConnected(): Boolean {
        val connectivityManger =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManger.activeNetwork ?: return false
        val actNetwork =
            connectivityManger.getNetworkCapabilities(networkCapabilities) ?: return false
        when {
            actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
            actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
            actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
        }

        return false
    }
}