package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.PostModel
import com.example.travelslkeheliya.R
import com.google.firebase.database.FirebaseDatabase

class PostUserAdapter(private var PostList:ArrayList<PostModel>): RecyclerView.Adapter<PostUserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostUserAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_list_item_user, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostUserAdapter.ViewHolder, position: Int) {
        val currentPost  = PostList[position]

        holder.tvPostLocation.text = currentPost.postLocation
        holder.tvPostContent.text = currentPost.postContent

        holder.btnDeletePost.setOnClickListener {
            val context = holder.itemView.context
            val alertDialog = AlertDialog.Builder(context)
                .setTitle("Confirm delete")
                .setMessage("Are you sure you want to delete this item?")
                .setPositiveButton("Delete") { _, _ ->
                    val deletedData = PostList[position]
                    FirebaseDatabase.getInstance().getReference("Posts").child(deletedData.postId.toString()).removeValue()
                    notifyItemRemoved(position)
                    Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancel", null)
                .create()
            alertDialog.show()
        }
    }

    override fun getItemCount(): Int {
        return PostList.size
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        val tvPostLocation : TextView = itemView.findViewById(R.id.tvPostLocation)
        val tvPostContent : TextView = itemView.findViewById(R.id.tvPostContent)
        val btnDeletePost : Button = itemView.findViewById(R.id.btnDeletePost)

    }
}