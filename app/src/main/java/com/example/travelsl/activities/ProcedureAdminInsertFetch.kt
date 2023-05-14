package com.example.travelsl.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.travelsl.R

class ProcedureAdminInsertFetch : AppCompatActivity() {

    private lateinit var btnInsertDoctorForm:Button
    private lateinit var btnFetchProcedureData:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.procedure_activity_admin_insert_fetch)

        btnInsertDoctorForm = findViewById(R.id.btnInsertProcedureForm)
        btnFetchProcedureData = findViewById(R.id.btnFetchProcedureData)

        btnInsertDoctorForm.setOnClickListener {
            val intent = Intent(this, ProcedureInsertActivity::class.java)
            startActivity(intent)
        }

        btnFetchProcedureData.setOnClickListener {
            val intent = Intent(this, ProcedureFetchWesternActivity::class.java)
            startActivity(intent)
        }
    }
}