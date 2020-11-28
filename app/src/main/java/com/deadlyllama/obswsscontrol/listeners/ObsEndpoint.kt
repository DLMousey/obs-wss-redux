package com.deadlyllama.obswsscontrol.listeners

import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.navigation.fragment.DialogFragmentNavigatorDestinationBuilder
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

import com.deadlyllama.obswsscontrol.models.ObsSceneList

class ObsEndpoint : WebSocketListener {

    private val TAG = "ObsEndpoint"
    private val NORMAL_CLOSURE_STATUS = 1000
    private var handler: Handler
    private lateinit var response: String

    constructor(handler: Handler) {
        this.handler = handler
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        Log.d(TAG, "onOpen: fired")
        Log.d(TAG, "onOpen: connection established")

        val message: Message = handler.obtainMessage()
        message.what = 0
        message.obj = "Connection established successfully"
        handler.sendMessage(message)
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        Log.d(TAG, "onMessage: fired")
        Log.d(TAG, "onMessage: received bytes! $bytes.toString()")

        val message: Message = handler.obtainMessage()
        message.what = 0
        message.obj = bytes.toString()
        handler.sendMessage(message)
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        Log.d(TAG, "onMessage: fired")
        Log.d(TAG, "onMessage: received text! $text")

        val gson = Gson()
        val obj: JsonObject = gson.fromJson(text, JsonObject::class.java)

        val message: Message = handler.obtainMessage()
        message.what = 0
        message.obj = obj
        handler.sendMessage(message)
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        Log.d(TAG, "onClosing: fired")
        webSocket.close(NORMAL_CLOSURE_STATUS, null)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        Log.d(TAG, "onFailure: fired")
        val message: Message = handler.obtainMessage()
        message.what = -1
        message.obj = t.localizedMessage
        handler.sendMessage(message)
    }

    fun getSceneList() {
        val gson: Gson = Gson()

        val message: Message = handler.obtainMessage()
        message.what = 1
        message.obj = gson.fromJson(response, ObsSceneList::class.java)
        handler.sendMessage(message)
    }

}