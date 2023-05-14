package com.example.travelsl2.Activities_Chamika

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.travelsl2.R

class UserMainHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_main_home)

        val btnHome: ImageView = findViewById(R.id.btnhome)
        val btnSearch:ImageView = findViewById(R.id.btnsearch)
        val btnHealth: ImageView = findViewById(R.id.btnhealth)
        val btnDashboard:ImageView = findViewById(R.id.btndashboard)


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