package com.example.travelsl2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.ScrollCaptureSession
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var btnsignup: Button

    private lateinit var btnlogin: Button
    private lateinit var uname: EditText
    private lateinit var pwd: EditText
    private lateinit var dbh: DBHelper

    lateinit var session: LoginPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        session = LoginPref(this)

        btnsignup = findViewById(R.id.signup)

        btnsignup.setOnClickListener {
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnlogin = findViewById(R.id.btnlogin)
        uname = findViewById(R.id.txtUsername)
        pwd = findViewById(R.id.txtPassword)
        dbh = DBHelper(this)

        btnlogin.setOnClickListener {
            val unametxt = uname.text.toString()
            val pwdtxt = pwd.text.toString()

            if (TextUtils.isEmpty(unametxt) || TextUtils.isEmpty(pwdtxt)) {
                Toast.makeText(this, "Username or Password missing", Toast.LENGTH_SHORT).show()
            }
            else {
                val checkuser = dbh.checkuserpass(unametxt, pwdtxt)
                if(checkuser==true) {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    session.createLoginSession(unametxt)
                    println(unametxt)
                    println(session.getUserDetails())
                    val intent = Intent(applicationContext, UserMainHome::class.java)
                    startActivity(intent)
                }
                else {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}