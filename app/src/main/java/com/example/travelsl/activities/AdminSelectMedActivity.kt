package com.example.travelsl.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.travelsl.R

class AdminSelectMedActivity : AppCompatActivity() {

    private lateinit var btnAdminDoctorInformation: Button
    private lateinit var btnAdminProcedureInformation: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_select_med)

        btnAdminDoctorInformation = findViewById(R.id.btnDoctors)
        btnAdminProcedureInformation = findViewById(R.id.btnProcedures)

        btnAdminDoctorInformation.setOnClickListener {
            val intent = Intent(this, DoctorAdminInsertFetch::class.java)
            startActivity(intent)
        }
        btnAdminProcedureInformation.setOnClickListener {
            val intent = Intent(this, ProcedureAdminInsertFetch::class.java)
            startActivity(intent)
        }
    }
}