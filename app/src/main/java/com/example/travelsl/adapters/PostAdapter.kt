package com.example.travelsl.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travelsl.models.PostModel
import com.example.travelsl.R

class PostAdapter(private var PostList:ArrayList<PostModel>): RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPost  = PostList[position]

        holder.tvPostLocation.text = currentPost.postLocation
        holder.tvPostContent.text = currentPost.postContent
    }

    override fun getItemCount(): Int {
        return PostList.size
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        val tvPostLocation : TextView = itemView.findViewById(R.id.tvPostLocation)
        val tvPostContent : TextView = itemView.findViewById(R.id.tvPostContent)

    }
}