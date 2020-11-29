package com.deadlyllama.obswsscontrol.services

import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import androidx.preference.PreferenceManager
import com.deadlyllama.obswsscontrol.listeners.ObsEndpoint
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import java.util.*

class WssConnectionService {

    fun createConnection(context: Context, handler: Handler): WebSocket {
        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val host = sharedPreferences.getString("setup_edittext_host", "192.168.1.1")
        val port = sharedPreferences.getString("setup_edittext_port", "4444")
        val req = Request.Builder().url("ws://$host:$port").build()

        val listener = ObsEndpoint(handler)
        val client = OkHttpClient().newBuilder().connectionSpecs(Arrays.asList(
                ConnectionSpec.CLEARTEXT
        )).build()

        val ws = client.newWebSocket(req, listener)
        client.dispatcher().executorService().shutdown()

        return ws
    }

}