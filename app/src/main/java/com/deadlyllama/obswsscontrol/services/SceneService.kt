package com.deadlyllama.obswsscontrol.services

import android.content.Context
import android.os.Handler

class SceneService {

    private var websocketService: WssConnectionService

    constructor() {
        websocketService = WssConnectionService()
    }

    fun getSceneList(context: Context, handler: Handler) {
        val ws = websocketService.createConnection(context, handler)
        val message = "{\"request-type\": \"GetSceneList\", \"message-id\": \"GetSceneList\"}"
        ws.send(message)
    }

    fun getCurrentScene(context: Context, handler: Handler) {
        val ws = websocketService.createConnection(context, handler)
        val message = "{\"request-type\": \"GetCurrentScene\", "
    }

    fun setCurrentScene(context: Context, handler: Handler, sceneName: String) {
        val ws = websocketService.createConnection(context, handler)
        val message = "{" +
                "\"request-type\": \"SetCurrentScene\", " +
                "\"message-id\": \"SetCurrentScene\"," +
                "\"scene-name\": \"$sceneName\"" +
                "}"

        ws.send(message)
    }


}