package com.example.travelsl.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.travelsl.R

class DoctorListItemActivity : AppCompatActivity() {

    private lateinit var tvDocNameItem:TextView
    private lateinit var tvDocMedCenterItem:TextView
    private lateinit var tvDocMedSpecItem:TextView
    private lateinit var tvDocTelephoneItem:TextView
    private lateinit var tvDocEmailItem:TextView
    private lateinit var btnUpdateDocRecord:Button
    private lateinit var btnDeleteDocRecord:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.doctor_activity_list_item)

        initView()

    }


    private fun initView() {
        tvDocNameItem = findViewById(R.id.tvDocNameItem)
        tvDocMedCenterItem = findViewById(R.id.tvDocMedCenterItem)
        tvDocMedSpecItem = findViewById(R.id.tvDocMedSpecItem)
        tvDocTelephoneItem = findViewById(R.id.tvDocTelephoneItem)
        tvDocEmailItem = findViewById(R.id.tvDocEmailItem)
        btnUpdateDocRecord = findViewById(R.id.btnUpdateDocRecord)
        btnDeleteDocRecord = findViewById(R.id.btnDeleteDocRecord)
    }
}