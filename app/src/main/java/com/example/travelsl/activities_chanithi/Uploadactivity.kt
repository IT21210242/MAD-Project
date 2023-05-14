package com.example.travelsl.activities_chanithi

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.travelsl.R
import com.example.travelsl.databinding.ActivityUploadactivityBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.text.DateFormat
import java.util.*

//this includes all the things related to new places adding part

class Uploadactivity : AppCompatActivity() {


    private lateinit var binding: ActivityUploadactivityBinding

    var imageURL : String? = null
    var uri:  Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val activityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == RESULT_OK){
                val data = result.data
                uri = data!!.data
                binding.eveLogo.setImageURI(uri)
            }else{
                Toast.makeText(this@Uploadactivity, "No image selected", Toast.LENGTH_SHORT).show()
            }
        }

        binding.eveLogo.setOnClickListener{
            val photoPicker = Intent(Intent.ACTION_PICK)
            photoPicker.type = "image/*"
            activityResultLauncher.launch(photoPicker)
        }

        binding.addBtn.setOnClickListener{
            saveData()
        }

    }

    private fun saveData() {
        val storageReference = FirebaseStorage.getInstance().reference.child("Task Images")
            .child(uri!!.lastPathSegment!!)
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setView(R.layout.progress_layout)
        val dialog = builder.create()
        dialog.show()
        storageReference.putFile(uri!!).addOnSuccessListener { taskSnapshot ->
            val uriTask = taskSnapshot.storage.downloadUrl
            while (!uriTask.isComplete);
            val urlImage = uriTask.result
            imageURL = urlImage.toString()
            uploadData()
            dialog.dismiss()
        }.addOnFailureListener {
            dialog.dismiss()
        }
    }

    private fun uploadData(){
        val dataID = binding.placeID.text.toString()
        val dataTitle = binding.eveName.text.toString()
        val dataDesc = binding.eveDescription.text.toString()
        val dataPriority = binding.evePriority.text.toString()

        val dataClass = DataClass(dataID,dataTitle,dataDesc,dataPriority,imageURL)

        val currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().time)

        FirebaseDatabase.getInstance().getReference("Most Recommended").child(dataID)
            .setValue(dataClass).addOnCompleteListener{task->
                if(task.isSuccessful){
                    Toast.makeText(this@Uploadactivity, "Saved", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }.addOnFailureListener{e->
                Toast.makeText(this@Uploadactivity, e.message.toString(), Toast.LENGTH_SHORT).show()
            }
    }
}
