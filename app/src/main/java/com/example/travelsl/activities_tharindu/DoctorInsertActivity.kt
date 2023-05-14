package com.example.travelsl.activities_tharindu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.travelsl.R
import com.example.travelsl.models.DoctorModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class  DoctorInsertActivity : AppCompatActivity() {

    private lateinit var etDoctorName:EditText
    private lateinit var etDoctorSpec:EditText
    private lateinit var etDoctorMedCenter:EditText
    private lateinit var etDoctorTel:EditText
    private lateinit var etDoctorEmail:EditText
    private lateinit var spnDoctorMedType: Spinner
    private lateinit var btnSubmitDoctorEntry: Button

    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.doctor_activity_insert)

        etDoctorName = findViewById(R.id.etDoctorName)
        etDoctorSpec = findViewById(R.id.etDoctorSpec)
        etDoctorMedCenter = findViewById(R.id.etDoctorMedCenter)
        etDoctorTel = findViewById(R.id.etDoctorTel)
        etDoctorEmail = findViewById(R.id.etDoctorEmail)
        spnDoctorMedType = findViewById(R.id.spnDoctorMedType)
        btnSubmitDoctorEntry = findViewById(R.id.btnSubmitDoctorEntry)

        dbRef = FirebaseDatabase.getInstance().getReference("Doctors")

        btnSubmitDoctorEntry.setOnClickListener {
            saveDoctorData()
        }

    }

    private fun saveDoctorData() {
        val doctorName = etDoctorName.text.toString()
        val doctorSpec = etDoctorSpec.text.toString()
        val doctorMedCenter = etDoctorMedCenter.text.toString()
        val doctorTel = etDoctorTel.text.toString()
        val doctorEmail = etDoctorEmail.text.toString()
        val doctorMedType = spnDoctorMedType.selectedItem.toString()

        if (doctorName.isEmpty()) {
            etDoctorName.error = "Please enter name"
        }
        if (doctorSpec.isEmpty()) {
            etDoctorSpec.error = "Please enter specialisation"
        }
        if (doctorMedCenter.isEmpty()) {
            etDoctorMedCenter.error = "Please enter medical center"
        }
        if (doctorTel.isEmpty()) {
            etDoctorTel.error = "Please enter telephone number"
        }
        if (doctorEmail.isEmpty()) {
            etDoctorEmail.error = "Please enter email"
        }

        val doctorId = dbRef.push().key.toString()

        val doctorDb = DoctorModel(doctorId,doctorName,doctorSpec,doctorMedCenter,doctorTel, doctorEmail, doctorMedType)

        dbRef.child(doctorId).setValue(doctorDb).addOnCompleteListener{
            Toast.makeText(this,"Data Inserted successfully", Toast.LENGTH_LONG).show()
            etDoctorName.text.clear()
            etDoctorSpec.text.clear()
            etDoctorMedCenter.text.clear()
            etDoctorTel.text.clear()
            etDoctorEmail.text.clear()
        }.addOnFailureListener{ err->
            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
        }
    }
}