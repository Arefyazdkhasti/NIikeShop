package com.example.nikeshop.utitlity

import android.content.Context
import android.net.ConnectivityManager

class NetWorkChecker(private val context: Context) {

    fun networkStatus(): Boolean {
        val conManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = conManager.activeNetworkInfo

        return (netInfo!=null && netInfo.isConnected)

    }
}