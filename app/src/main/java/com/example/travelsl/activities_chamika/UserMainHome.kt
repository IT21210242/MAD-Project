package com.example.travelsl.activities_chamika

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.travelsl.R
import com.example.travelsl.activities_chanithi.MainActivityChanithi
import com.example.travelsl.activities_keheliya.CreateNewPostForm
import com.example.travelsl.activities_keheliya.FetchAllPosts
import com.example.travelsl.activities_keheliya.FetchAllPostsUser
import com.example.travelsl.activities_tharindu.UserSelectMedActivity

class UserMainHome : AppCompatActivity() {
    private lateinit var btnCreateNewPost: Button
    private lateinit var btnWhatsNew: Button
    private lateinit var btnYourPosts: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_main_home)

        val btnHome: ImageView = findViewById(R.id.btnhome)
        val btnSearch:ImageView = findViewById(R.id.btnsearch)
        val btnHealth: ImageView = findViewById(R.id.btnhealth)
        val btnDashboard:ImageView = findViewById(R.id.btndashboard)

        btnCreateNewPost = findViewById(R.id.btnCreateNewPost2)
        btnWhatsNew = findViewById(R.id.btnWhatsNew2)
        btnYourPosts = findViewById(R.id.btnYourPosts2)

        btnCreateNewPost.setOnClickListener {
            val intent = Intent(this, CreateNewPostForm::class.java)
            startActivity(intent)
        }
        btnYourPosts.setOnClickListener {
            val intent = Intent(this, FetchAllPostsUser::class.java)
            startActivity(intent)
        }
        btnWhatsNew.setOnClickListener {
            val intent = Intent(this, FetchAllPosts::class.java)
            startActivity(intent)
        }



        btnHome.setOnClickListener {
            val intent = Intent(applicationContext, UserMainHome::class.java)
            startActivity(intent)
        }

        btnSearch.setOnClickListener {
            val intent = Intent(applicationContext, MainActivityChanithi::class.java)
            startActivity(intent)
        }

        btnHealth.setOnClickListener {
            val intent = Intent(applicationContext, UserSelectMedActivity::class.java)
            startActivity(intent)
        }

        btnDashboard.setOnClickListener {
            val intent = Intent(applicationContext, Dashboard::class.java)
            startActivity(intent)
        }
    }
}