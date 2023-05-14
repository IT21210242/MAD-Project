package com.example.travelsl.activities_keheliya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travelsl.adapters.PostAdapter
import com.example.travelsl.models.PostModel
import com.google.firebase.database.*
import com.example.travelsl.R

class FetchAllPosts : AppCompatActivity() {


    private lateinit var rvPosts:RecyclerView
    private lateinit var tvLoadingData:TextView
    private lateinit var postList:ArrayList<PostModel>
    private lateinit var dbRef : DatabaseReference
    private lateinit var buttonSet:LinearLayout

    private lateinit var btnYourPosts:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_all_posts)

        rvPosts = findViewById(R.id.rvPosts)
        rvPosts.layoutManager = LinearLayoutManager(this)
        rvPosts.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)
        buttonSet = findViewById(R.id.buttonSet)
        btnYourPosts = findViewById(R.id.btnYourPosts)

        postList = arrayListOf<PostModel>()

        btnYourPosts.setOnClickListener {
            val intent = Intent(this, FetchAllPostsUser::class.java)
            startActivity(intent)
        }

        getPostData()
    }

    private fun getPostData() {
        rvPosts.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE
        buttonSet.visibility = View.GONE

        dbRef = FirebaseDatabase.getInstance().getReference("Posts")

        dbRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                postList.clear()
                if (snapshot.exists()){
                    for (docSnap in snapshot.children){
                        val docData = docSnap.getValue(PostModel::class.java)
                        postList.add(docData!!)
                    }
                    val mAdapter = PostAdapter(postList)
                    rvPosts.adapter=mAdapter

                    rvPosts.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                    buttonSet.visibility = View.VISIBLE
                }
                else{
                    val intent = Intent(this@FetchAllPosts, CreateNewPostForm::class.java)
                    startActivity(intent)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}