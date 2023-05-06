package com.example.travelslkeheliya.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.models.PostModel
import com.example.travelslkeheliya.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CreateNewPostForm : AppCompatActivity() {

    private lateinit var etInsertLocation: EditText
    private lateinit var etPostArticle: EditText

    private lateinit var btnSubmitPost: Button
    private lateinit var btnCancelPost: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_post_form)

        etInsertLocation = findViewById(R.id.etInsertLocation)
        etPostArticle = findViewById(R.id.etPostArticle)
        btnSubmitPost = findViewById(R.id.btnSubmitPost)
        btnCancelPost = findViewById(R.id.btnCancelPost)

        dbRef = FirebaseDatabase.getInstance().getReference("Posts")

        btnSubmitPost.setOnClickListener {
            savePost()
        }

        btnCancelPost.setOnClickListener {
            val intent = Intent(this, CommunityPosts::class.java)
            startActivity(intent)
        }
    }

    private fun savePost() {
        val postLocation = etInsertLocation.text.toString()
        val postArticle = etPostArticle.text.toString()

        if(postArticle.isEmpty()){
            etPostArticle.error = "Please Enter location"
        }
        if(postLocation.isEmpty()){
            etInsertLocation.error = "Please Enter location"
        }

        val postId = dbRef.push().key.toString()

        val postDb = PostModel(postId = postId, postLocation = postLocation, postContent = postArticle)

        dbRef.child(postId).setValue(postDb).addOnCompleteListener{
            Toast.makeText(this,"Data Inserted successfully", Toast.LENGTH_LONG).show()
            etInsertLocation.text.clear()
            etPostArticle.text.clear()
        }.addOnFailureListener{ err->
            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
        }
    }
}