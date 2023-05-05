package com.example.travelsl2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    private lateinit var btnlogin: Button

    private lateinit var uname: EditText
    private lateinit var pwd: EditText
    private lateinit var name: EditText
    private lateinit var email: EditText
    private  lateinit var signupbtn: Button
    private lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        btnlogin = findViewById(R.id.login)

        btnlogin.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        uname = findViewById(R.id.txtUsername)
        pwd = findViewById(R.id.txtPassword)
        name = findViewById(R.id.txtname)
        email = findViewById(R.id.txtEmail)
        signupbtn = findViewById(R.id.btnlogin)
        db = DBHelper(this)

        signupbtn.setOnClickListener {
            val unametxt = uname.text.toString()
            val pwdtxt = pwd.text.toString()
            val nametxt = name.text.toString()
            val emailtxt = email.text.toString()
            val savedata = db.insertdata(unametxt, pwdtxt, nametxt, emailtxt)

            if (TextUtils.isEmpty(unametxt) || TextUtils.isEmpty(pwdtxt) || TextUtils.isEmpty(nametxt) || TextUtils.isEmpty(emailtxt)) {
                Toast.makeText(this, "Add username, email, name or password", Toast.LENGTH_SHORT).show()
            }
            else {
                if(savedata==true) {
                    Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }
                else {
                    Toast.makeText(this, "Signup Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}