package com.example.travelsl.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.travelsl.R

class UserSelectMedActivity : AppCompatActivity() {

    private lateinit var btnDoctors: Button
    private lateinit var btnProcedures: Button
    private lateinit var btnMoreInfo:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_select_med)

        btnDoctors = findViewById(R.id.btnDoctors)
        btnProcedures = findViewById(R.id.btnProcedures)
        btnMoreInfo = findViewById(R.id.btnMoreInfo)

        btnDoctors.setOnClickListener {
            val intent = Intent(this, DoctorFetchWesternUserActivity::class.java)
            startActivity(intent)
        }

        btnProcedures.setOnClickListener {
            val intent = Intent(this, ProcedureFetchWesternUserActivity::class.java)
            startActivity(intent)
        }

        btnMoreInfo.setOnClickListener {
            val intent = Intent(this, AboutAyurvedicMedicine::class.java)
            startActivity(intent)
        }
    }


}