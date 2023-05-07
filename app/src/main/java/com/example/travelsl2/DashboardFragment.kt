package com.example.travelsl2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

// Define a class for the Dashboard fragment
class DashboardFragment : Fragment() {

    // Override the onCreateView method to create and return the view for this fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        // Find the buttons defined in the layout by their IDs and assign them to variables
        val btnSettings = view?.findViewById<Button>(R.id.settingsbtn)
        val btnTodo = view?.findViewById<Button>(R.id.todobtn)
        val btnUserProfile = view?.findViewById<Button>(R.id.profilebtn)
        // Create instances of the DashboardFragment and UserProfileFragment classes
        val dashboardFragment = DashboardFragment()
        val userProfileFragment = UserProfileFragment()


        btnSettings?.setOnClickListener {

        }

        btnTodo?.setOnClickListener {
            val intent = Intent(activity, TodoActivity::class.java)
            startActivity(intent)
        }

        // Set a click listener on the "Profile" button
        btnUserProfile?.setOnClickListener {
            // Create an intent to start the UserProfile activity
            val intent = Intent(activity, UserProfile::class.java)
            startActivity(intent)

//            val fragment = UserProfileFragment.newInstance()
//            val transaction = fragmentManager?.beginTransaction()
//            transaction?.replace(R.id.fragmentContainerView, fragment)
//            transaction?.addToBackStack(null)
//            transaction?.commit()
        }
        // Return the view for this fragment
        return view
    }

}