package com.example.travelsl.activities_keheliya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.travelsl.R

class CommunityPosts : AppCompatActivity() {

    private lateinit var btnCreateNewPost: Button
    private lateinit var btnWhatsNew:Button
    private lateinit var btnYourPosts:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_posts)

        btnCreateNewPost = findViewById(R.id.btnCreateNewPost)
        btnWhatsNew = findViewById(R.id.btnWhatsNew)
        btnYourPosts = findViewById(R.id.btnYourPosts)

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
    }
}