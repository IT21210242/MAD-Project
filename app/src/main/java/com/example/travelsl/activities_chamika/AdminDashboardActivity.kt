package com.example.travelsl.activities_chamika

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.travelsl.R
import com.example.travelsl.activities_tharindu.AdminSelectMedActivity

class AdminDashboardActivity : AppCompatActivity()  {
    private lateinit var settings: Button
    private lateinit var profile: Button
    private lateinit var newAdmin: Button
    private lateinit var health: Button
    private lateinit var signout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_dashboard)

        settings = findViewById(R.id.settings)
        profile = findViewById(R.id.profile)
        newAdmin = findViewById(R.id.newAdmin)
        health = findViewById(R.id.healthbtn)
        signout = findViewById(R.id.adminsignout)

        signout.setOnClickListener {
            val intent = Intent(applicationContext, AdminLoginActivity::class.java)
            startActivity(intent)
        }

        settings.setOnClickListener {

        }

        profile.setOnClickListener {
            val intent = Intent(applicationContext, AdminProfile::class.java)
            startActivity(intent)
        }

        newAdmin.setOnClickListener {
            val intent = Intent(applicationContext, AdminRegisterActivity::class.java)
            startActivity(intent)
        }

        health.setOnClickListener {
            val intent = Intent(applicationContext, AdminSelectMedActivity::class.java)
            startActivity(intent)
        }
    }
}