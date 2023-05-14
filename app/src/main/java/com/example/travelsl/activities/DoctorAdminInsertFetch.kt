package com.example.travelsl.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.travelsl.R

class DoctorAdminInsertFetch : AppCompatActivity() {

    private lateinit var btnInsertDoctorForm: Button
    private lateinit var btnFetchDoctorRecords: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.doctor_activity_admin_insert_fetch)

        btnInsertDoctorForm = findViewById(R.id.btnInsertDoctorForm)
        btnFetchDoctorRecords = findViewById(R.id.btnFetchDocData)

        btnInsertDoctorForm.setOnClickListener {
            val intent = Intent(this, DoctorInsertActivity::class.java)
            startActivity(intent)
        }

        btnFetchDoctorRecords.setOnClickListener {
            val intent = Intent(this, DoctorFetchWesternActivity::class.java)
            startActivity(intent)
        }
    }

}