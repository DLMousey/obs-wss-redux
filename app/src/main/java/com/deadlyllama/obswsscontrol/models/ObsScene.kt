package com.deadlyllama.obswsscontrol.models

import com.google.gson.JsonElement
import com.google.gson.JsonObject

class ObsScene {

    var name: String? = null
    var sources: ArrayList<ObsSource>? = null

    constructor(name: String, sources: ArrayList<ObsSource>) {
        this.name = name
        this.sources = sources
    }

    constructor() {}

    fun fromJsonElement(element: JsonElement): ObsScene {
        val scene: ObsScene = ObsScene()

        val sceneObject: JsonObject = element.asJsonObject
        scene.name = sceneObject.get("name").asString
        scene.sources = ObsSource.fromJsonArray(sceneObject.get("sources").asJsonArray)

        return scene

    }
}