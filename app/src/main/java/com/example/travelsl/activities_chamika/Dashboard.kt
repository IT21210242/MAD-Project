package com.example.travelsl.activities_chamika

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.travelsl.R
import com.example.travelsl.activities_chanithi.MainActivityChanithi
import com.example.travelsl.activities_tharindu.UserSelectMedActivity

// Defining the Dashboard activity class that extends the AppCompatActivity class
class Dashboard : AppCompatActivity() {
    // The onCreate method is called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Setting the layout of the activity using the setContentView method
        setContentView(R.layout.activity_dashboard)

        // Retrieving references to UI elements using findViewById
        val btnHome: ImageView = findViewById(R.id.btnhome2)
        val btnSearch: ImageView = findViewById(R.id.btnsearch2)
        val btnHealth: ImageView = findViewById(R.id.btnhealth2)
        val btnDashboard: ImageView = findViewById(R.id.btndashboard2)

        val settings: Button = findViewById(R.id.settings)
        val planner: Button = findViewById(R.id.newAdmin)
        val profile: Button = findViewById(R.id.profile)
        val signout: Button = findViewById(R.id.signoutbtn2)

        // Setting the click listeners for buttons
        settings.setOnClickListener {

        }

        planner.setOnClickListener {
            // Starting new planner activity on button click
            val intent = Intent(applicationContext, TodoActivity::class.java)
            startActivity(intent)
        }

        profile.setOnClickListener {
            // Starting new profile activity on button click
            val intent = Intent(applicationContext, UserProfile::class.java)
            startActivity(intent)
        }

        signout.setOnClickListener {
            // Starting new sign out activity on button click
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        btnHome.setOnClickListener {
            // Starting new home activity on button click
            val intent = Intent(applicationContext, UserMainHome::class.java)
            startActivity(intent)

        }

        btnSearch.setOnClickListener {
            // Starting new search activity on button click
            val intent = Intent(applicationContext, MainActivityChanithi::class.java)
            startActivity(intent)
        }

        btnHealth.setOnClickListener {
            // Starting new health activity on button click
            val intent = Intent(applicationContext, UserSelectMedActivity::class.java)
            startActivity(intent)
        }

        btnDashboard.setOnClickListener {
            // Starting new dashboard activity on button click
            val intent = Intent(applicationContext, Dashboard::class.java)
            startActivity(intent)
        }
    }
}