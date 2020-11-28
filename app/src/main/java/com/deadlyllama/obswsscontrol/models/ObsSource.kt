package com.deadlyllama.obswsscontrol.models

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject

class ObsSource {

    var cx: Double? = null
    var cy: Double? = null
    var id: Number? = null
    var locked: Boolean = false
    var name: String? = null
    var parentGroupName: String? = null
    var render: Boolean = false
    var sourceCx: Double? = null
    var sourceCy: Double? = null
    var type: String? = null
    var volume: Double? = null
    var x: Double? = null
    var y: Double? = null
    var groupChildren: java.util.ArrayList<ObsSource>? = null

    companion object {
        @JvmStatic
        fun fromJsonElement(element: JsonElement): ObsSource {
            val source: ObsSource = ObsSource()
            val obj: JsonObject = element.asJsonObject

            source.cx = obj.get("cx").asDouble
            source.cy = obj.get("cy").asDouble
            source.id = obj.get("id").asInt
            source.locked = obj.get("locked").asBoolean
            source.name = obj.get("name").asString
            source.render = obj.get("render").asBoolean
            source.sourceCx = obj.get("source_cx").asDouble
            source.sourceCy = obj.get("source_cy").asDouble
            source.type = obj.get("type").asString
            source.volume = obj.get("volume").asDouble
            source.x = obj.get("x").asDouble
            source.y = obj.get("y").asDouble

            return source
        }

        @JvmStatic
        fun fromJsonArray(array: JsonArray): java.util.ArrayList<ObsSource> {
            val sources: ArrayList<ObsSource> = java.util.ArrayList<ObsSource>()

            for (obj: JsonElement in array) {
                val source: ObsSource = ObsSource()
                val o: JsonObject = obj.asJsonObject

                source.cx = o.get("cx").asDouble
                source.cy = o.get("cy").asDouble
                source.id = o.get("id").asInt
                source.locked = o.get("locked").asBoolean
                source.name = o.get("name").asString
                source.render = o.get("render").asBoolean
                source.sourceCx = o.get("source_cx").asDouble
                source.sourceCy = o.get("source_cy").asDouble
                source.type = o.get("type").asString
                source.volume = o.get("volume").asDouble
                source.x = o.get("x").asDouble
                source.y = o.get("y").asDouble

                sources.add(source)
            }

            return sources
        }
    }

}