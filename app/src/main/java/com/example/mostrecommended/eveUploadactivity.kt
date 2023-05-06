package com.example.mostrecommended

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mostrecommended.databinding.EveactivityUploadactivityBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.text.DateFormat
import java.util.Calendar

//event upload activity includes the activities related to uploading new events

class eveUploadactivity : AppCompatActivity() {

    private lateinit var binding: EveactivityUploadactivityBinding
    var imageURL : String? = null
    var uri:  Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EveactivityUploadactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val activityResultLauncher2 = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()){result ->
            if(result.resultCode == RESULT_OK){
                val data = result.data
                uri = data!!.data
                binding.eveLogo.setImageURI(uri)
            }else{
                Toast.makeText(this@eveUploadactivity, "No image selected",Toast.LENGTH_SHORT).show()
            }
        }

        binding.eveLogo.setOnClickListener{
            val photoPicker2 = Intent(Intent.ACTION_PICK)
            photoPicker2.type = "image/*"
            activityResultLauncher2.launch(photoPicker2)
        }

        binding.addBtn.setOnClickListener{
            saveData()
        }

    }

    private fun saveData() {
        val storageReference2 = FirebaseStorage.getInstance().reference.child("Task Images")
            .child(uri!!.lastPathSegment!!)
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setView(R.layout.eveprogress_layout)
        val dialog = builder.create()
        dialog.show()
        storageReference2.putFile(uri!!).addOnSuccessListener { taskSnapshot ->
            val uriTask2 = taskSnapshot.storage.downloadUrl
            while (!uriTask2.isComplete);
            val urlImage2 = uriTask2.result
            imageURL = urlImage2.toString()
            uploadData()
            dialog.dismiss()
        }.addOnFailureListener {
            dialog.dismiss()
        }
    }

    private fun uploadData(){
        val dataTitle = binding.eveName.text.toString()
        val dataDesc = binding.eveDescription.text.toString()
        val dataPriority = binding.evePriority.text.toString()

        val dataClass = eveDataClass(dataTitle,dataDesc,dataPriority,imageURL)
        val currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().time)

        FirebaseDatabase.getInstance().getReference("eventCalendar").child(currentDate)
            .setValue(dataClass).addOnCompleteListener{task->
                if(task.isSuccessful){
                    Toast.makeText(this@eveUploadactivity, "Saved", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }.addOnFailureListener{e->
                Toast.makeText(this@eveUploadactivity, e.message.toString(),Toast.LENGTH_SHORT).show()
            }
    }
}