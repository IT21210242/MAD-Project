package com.example.travelsl.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.travelsl.R

class MainActivity : AppCompatActivity() {

    private lateinit var btnClick: Button
    private lateinit var message: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun changeText(v: View){
        btnClick = findViewById(R.id.btnClick)
        message = findViewById(R.id.tvMessage)
        message.text = "Hello, User!"

    }

}