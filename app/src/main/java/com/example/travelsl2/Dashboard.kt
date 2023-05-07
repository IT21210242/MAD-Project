package com.example.travelsl2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

// Defining the Dashboard activity class that extends the AppCompatActivity class
class Dashboard : AppCompatActivity() {
    // The onCreate method is called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Setting the layout of the activity using the setContentView method
        setContentView(R.layout.activity_dashboard)

        val btnHome: ImageView = findViewById(R.id.btnhome2)
        val btnSearch: ImageView = findViewById(R.id.btnsearch2)
        val btnHealth: ImageView = findViewById(R.id.btnhealth2)
        val btnDashboard: ImageView = findViewById(R.id.btndashboard2)

        val settings: Button = findViewById(R.id.settings)
        val planner: Button = findViewById(R.id.todo)
        val profile: Button = findViewById(R.id.profile)
        val signout: Button = findViewById(R.id.signoutbtn2)

        settings.setOnClickListener {

        }

        planner.setOnClickListener {
            val intent = Intent(applicationContext, TodoActivity::class.java)
            startActivity(intent)
        }

        profile.setOnClickListener {
            val intent = Intent(applicationContext, UserProfile::class.java)
            startActivity(intent)
        }

        signout.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        btnHome.setOnClickListener {
            val intent = Intent(applicationContext, UserMainHome::class.java)
            startActivity(intent)

        }

        btnSearch.setOnClickListener {

        }

        btnHealth.setOnClickListener {

        }

        btnDashboard.setOnClickListener {
            val intent = Intent(applicationContext, Dashboard::class.java)
            startActivity(intent)
        }
    }
}