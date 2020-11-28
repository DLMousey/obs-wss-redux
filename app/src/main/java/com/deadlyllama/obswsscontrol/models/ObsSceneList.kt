package com.deadlyllama.obswsscontrol.models

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class ObsSceneList {

    @SerializedName("current-scene")
    var currentScene: String? = null

    @SerializedName("message-id")
    var messageId: String? = null

    var scenes: ArrayList<ObsScene>? = null

    companion object {
        @JvmStatic
        fun fromJsonObject(jsonObject: JsonObject): ObsSceneList {
            val sceneList: ObsSceneList = ObsSceneList()
            sceneList.currentScene = jsonObject.get("current-scene").asString
            sceneList.messageId = jsonObject.get("message-id").asString
            sceneList.scenes = ArrayList()

            val list: JsonArray = jsonObject.get("scenes").asJsonArray

            for (item: JsonElement in list) {
                val scene: ObsScene = ObsScene().fromJsonElement(item)
                sceneList.scenes!!.add(scene)
            }

            return sceneList
        }
    }
}