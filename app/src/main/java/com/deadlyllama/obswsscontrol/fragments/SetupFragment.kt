package com.deadlyllama.obswsscontrol.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.preference.PreferenceFragment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.deadlyllama.obswsscontrol.R
import com.deadlyllama.obswsscontrol.listeners.ObsEndpoint
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.*

class SetupFragment : PreferenceFragmentCompat() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        if (preference != null) {
            if (preference.key.equals("setup_button_test")) {

                val host = preferenceManager.sharedPreferences.getString("setup_edittext_host", "192.168.1.1")
                val port = preferenceManager.sharedPreferences.getString("setup_edittext_port", "4444")
                val req = Request.Builder().url("ws://$host:$port").build()

                val handler = object : Handler() {
                    override fun handleMessage(msg: Message) {
                        Log.d("PREF_TREE_CLICK_HANDLER", "handleMessage: fired outside background thread")

                        if (msg.what == 0) {
                            Toast.makeText(context, "Success: " + msg.obj.toString(), Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Failed: " + msg.obj.toString(), Toast.LENGTH_SHORT).show()
                        }

                        super.handleMessage(msg)
                    }
                }
                val listener = ObsEndpoint(handler)
                val client = OkHttpClient().newBuilder().connectionSpecs(Arrays.asList(
                    ConnectionSpec.CLEARTEXT
                )).build()

                val ws = client.newWebSocket(req, listener)
                client.dispatcher().executorService().shutdown()

                Toast.makeText(context, "Testing WSS Connection...", Toast.LENGTH_SHORT).show()
                return true
            }

            return false
        }

        return false
    }
}