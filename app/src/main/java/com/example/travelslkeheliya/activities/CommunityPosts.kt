package com.example.travelslkeheliya.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.travelslkeheliya.R

class CommunityPosts : AppCompatActivity() {

    private lateinit var btnCreateNewPost: Button
    private lateinit var btnWhatsNew:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_posts)

        btnCreateNewPost = findViewById(R.id.btnCreateNewPost)
        btnWhatsNew = findViewById(R.id.btnWhatsNew)

        btnCreateNewPost.setOnClickListener {
            val intent = Intent(this, CreateNewPostForm::class.java)
            startActivity(intent)
        }
        btnWhatsNew.setOnClickListener {
            val intent = Intent(this, FetchAllPosts::class.java)
            startActivity(intent)
        }
    }
}