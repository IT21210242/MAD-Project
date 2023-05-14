package com.example.travelsl.activities_chanithi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.travelsl.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

//this includes the activities related to deleting existing places from the system
class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteBtn.setOnClickListener{
            val deleteID = binding.deleteID.text.toString()
            if(deleteID.isNotEmpty()){
                deleteData(deleteID)
            }else{
                Toast.makeText(this,"Please enter place ID",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteData(deleteID : String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Most Recommended")
        databaseReference.child(deleteID).removeValue().addOnSuccessListener {
            binding.deleteID.text.clear()
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{
            Toast.makeText(this, "Unable to delete", Toast.LENGTH_SHORT).show()
        }
    }
}