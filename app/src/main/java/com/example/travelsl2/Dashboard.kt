package com.example.travelsl2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

// Defining the Dashboard activity class that extends the AppCompatActivity class
class Dashboard : AppCompatActivity() {
    // The onCreate method is called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Setting the layout of the activity using the setContentView method
        setContentView(R.layout.activity_dashboard)
    }
}