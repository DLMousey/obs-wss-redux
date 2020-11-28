package com.deadlyllama.obswsscontrol.fragments

import android.os.Bundle
import android.transition.Visibility
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.deadlyllama.obswsscontrol.R

/**
 * A simple [Fragment] subclass.
 * Use the [LandingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LandingFragment : Fragment(), View.OnClickListener {

    lateinit var navHostFragment: NavHostFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_landing, container, false)

        val easterEgg = view.findViewById<TextView>(R.id.landing_title_nuts)
        val easterEggTrigger = view.findViewById<TextView>(R.id.landing_title_gag)
        easterEggTrigger.setOnClickListener {
            if (easterEgg.isVisible) {
                easterEgg.visibility = View.INVISIBLE
            } else {
                easterEgg.visibility = View.VISIBLE
            }
        }

        view.findViewById<Button>(R.id.landing_button_setup).setOnClickListener(this)
        view.findViewById<Button>(R.id.landing_button_scenes).setOnClickListener(this)

        return view
    }

    override fun onClick(view: View) {
        var action: NavDirections? = null

        Log.d("landingfragment", "onClick: started, " + view.id)

        when(view.id) {
            R.id.landing_button_setup -> {
                action = LandingFragmentDirections.actionLandingFragmentToSetupFragment()
            }
            R.id.landing_button_scenes -> {
                action = LandingFragmentDirections.actionLandingFragmentToSceneListFragment()
            }
        }

        if (action != null) {
            view.findNavController().navigate(action)
        } else {
            Toast.makeText(view.context, "No idea mate", Toast.LENGTH_SHORT).show()
        }
    }
}