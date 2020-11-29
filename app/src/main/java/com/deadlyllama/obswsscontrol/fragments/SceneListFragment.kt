package com.deadlyllama.obswsscontrol.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.deadlyllama.obswsscontrol.R
import com.deadlyllama.obswsscontrol.models.ObsScene
import com.deadlyllama.obswsscontrol.models.ObsSceneList
import com.deadlyllama.obswsscontrol.services.SceneService
import com.google.gson.JsonObject

class SceneListFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_scene_list, container, false)
        val sceneListLayout = view.findViewById<LinearLayout>(R.id.sceneList_linearLayout_scenes)

        val handler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message) {
                if (msg.what == 0) {
                    val data: JsonObject = msg.obj as JsonObject
                    val sceneList: ObsSceneList = ObsSceneList.fromJsonObject(data)

                    if (sceneList.scenes == null) {
                        return
                    }

                    for (scene: ObsScene in sceneList.scenes!!) {
                        val btn: Button = Button(context)
                        btn.text = scene.name
                        btn.layoutParams = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                300,
                        )
                        btn.setPadding(50, 50, 50, 50)
                        btn.setOnClickListener {
                            val btn: Button = it as Button
                            val sceneName = btn.text

                            @SuppressLint("HandlerLeak")
                            val handler = object : Handler() {}

                            val service = SceneService()
                            context?.let {
                                service.setCurrentScene(
                                        it.applicationContext,
                                        handler,
                                        sceneName as String
                                )
                            }
                        }

                        sceneListLayout.addView(btn)
                    }
                }

                super.handleMessage(msg)
            }
        }

        val service = SceneService()
        context?.let { service.getSceneList(it, handler) }

        return view
    }

    override fun onClick(v: View?) {

    }
}