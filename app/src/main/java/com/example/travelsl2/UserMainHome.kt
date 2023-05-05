package com.example.travelsl2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class UserMainHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_main_home)

        val btnHome: ImageView = findViewById(R.id.btnhome)
        val btnSearch:ImageView = findViewById(R.id.btnsearch)
        val btnHealth: ImageView = findViewById(R.id.btnhealth)
        val btnDashboard:ImageView = findViewById(R.id.btndashboard)
        val fragmentDashboard = DashboardFragment()
        val fragmentTest = TestFragment()

        btnHome.setOnClickListener {
            btnHome.setImageResource(R.drawable.selected_home)
            btnSearch.setImageResource(R.drawable.unselected_search)
            btnHealth.setImageResource(R.drawable.unselected_health)
            btnDashboard.setImageResource(R.drawable.unselected_dashboard)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView,fragmentTest)
                commit()
            }
        }

        btnSearch.setOnClickListener {
            btnHome.setImageResource(R.drawable.unselected_home)
            btnSearch.setImageResource(R.drawable.selected_search)
            btnHealth.setImageResource(R.drawable.unselected_health)
            btnDashboard.setImageResource(R.drawable.unselected_dashboard)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView,fragmentTest)
                commit()
            }
        }

        btnHealth.setOnClickListener {
            btnHome.setImageResource(R.drawable.unselected_home)
            btnSearch.setImageResource(R.drawable.unselected_search)
            btnHealth.setImageResource(R.drawable.selected_health)
            btnDashboard.setImageResource(R.drawable.unselected_dashboard)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView,fragmentTest)
                commit()
            }
        }

        btnDashboard.setOnClickListener {
            btnHome.setImageResource(R.drawable.unselected_home)
            btnSearch.setImageResource(R.drawable.unselected_search)
            btnHealth.setImageResource(R.drawable.unselected_health)
            btnDashboard.setImageResource(R.drawable.selected_dashboard)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainerView,fragmentDashboard)
                commit()
            }
        }
    }
}