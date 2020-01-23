package com.nayhtetlynn.currencyrate.network_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class ConnectivityReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        when(intent?.action){
            Intent.ACTION_BOOT_COMPLETED->{
                if (connectivityReceiverListener != null) {
                    connectivityReceiverListener!!.onNetworkConnectionChanged(isConnectedOrConnecting(context!!))
                }
            }
            Intent.ACTION_DATE_CHANGED->{
                if (connectivityReceiverListener != null) {
                    connectivityReceiverListener!!.onNetworkConnectionChanged(isConnectedOrConnecting(context!!))
                }
            }
            else->{
                if (connectivityReceiverListener != null) {
                    connectivityReceiverListener!!.onNetworkConnectionChanged(isConnectedOrConnecting(context!!))
                }
            }
        }

    }

    private fun isConnectedOrConnecting(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw      = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }

//        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val networkInfo = connMgr.activeNetworkInfo
//        return networkInfo != null && networkInfo.isConnected
    }

    interface ConnectivityReceiverListener {
        fun onNetworkConnectionChanged(isConnected: Boolean)
    }

    companion object {
        var connectivityReceiverListener: ConnectivityReceiverListener? = null
    }
}