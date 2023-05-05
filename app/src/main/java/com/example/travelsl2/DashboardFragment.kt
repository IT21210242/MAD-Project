package com.example.travelsl2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment


class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val btnSettings = view?.findViewById<Button>(R.id.settingsbtn)
        val btnTodo = view?.findViewById<Button>(R.id.todobtn)
        val btnUserProfile = view?.findViewById<Button>(R.id.profilebtn)
        val dashboardFragment = DashboardFragment()
        val userProfileFragment = UserProfileFragment()

        btnSettings?.setOnClickListener {

        }

        btnTodo?.setOnClickListener {

        }

        btnUserProfile?.setOnClickListener {
            val intent = Intent(activity, UserProfile::class.java)
            startActivity(intent)

//            val fragment = UserProfileFragment.newInstance()
//            val transaction = fragmentManager?.beginTransaction()
//            transaction?.replace(R.id.fragmentContainerView, fragment)
//            transaction?.addToBackStack(null)
//            transaction?.commit()
        }
        return view
    }

}