package com.example.travelsl.activities_chanithi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.travelsl.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

//this includes the activities related to update places part

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateBtn.setOnClickListener{
            val placeID = binding.placeID.text.toString()
            val placeName = binding.placeName.text.toString()
            val districtUpdate = binding.districtUpdate.text.toString()
            val descUpdate = binding.descUpdate.text.toString()

            updateData(placeID,placeName,districtUpdate,descUpdate)
        }
    }

    private fun updateData(placeID:String,dataTitle: String, dataPriority: String, dataDesc:String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Most Recommended")
        val user = mapOf<String, String>("dataTitle" to dataTitle,"dataPriority" to dataPriority, "dataDesc" to dataDesc)
        databaseReference.child(placeID).updateChildren(user).addOnSuccessListener {
            binding.placeName.text.clear()
            binding.districtUpdate.text.clear()
            binding.descUpdate.text.clear()
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this, "Unable to Update", Toast.LENGTH_SHORT).show()
        }
    }
}