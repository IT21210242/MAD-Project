package com.example.travelsl2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class UserProfile : AppCompatActivity() {
    lateinit var session: LoginPref
    private lateinit var dbh: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val txtuname = findViewById<TextView>(R.id.updateuname)
        val txtname = findViewById<TextView>(R.id.updatename)
        val txtemail = findViewById<TextView>(R.id.updateEmail)
        val txtpwd = findViewById<TextView>(R.id.updatepwdtxt2)
        val updatebtn = findViewById<Button>(R.id.updateprofile)
        val deletebtn = findViewById<Button>(R.id.delete)

        val btnHome: ImageView = findViewById(R.id.backbtn1)

        dbh = DBHelper(this)
        session = LoginPref(this)
        var username: String = session.getUserDetails()
        println(username)
        val userdetails: UserModel = dbh.getUser(username)

        val uname: String = userdetails.username
        val name: String = userdetails.name
        val email: String = userdetails.email
        val password: String = userdetails.password

        txtuname?.setText(uname, null)
        txtname?.setText(name, null)
        txtemail?.setText(email, null)
        txtpwd?.setText(password, null)

        updatebtn.setOnClickListener {
            val nameupdate = txtname.text.toString()
            val emailupdate = txtemail.text.toString()
            val pwdupdate = txtpwd.text.toString()

            val updateUser = dbh.updatedata(uname, pwdupdate, nameupdate, emailupdate)

            if(updateUser) {
                Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show()
            }

            val intent = Intent(applicationContext, UserProfile::class.java)
            startActivity(intent)
        }

        deletebtn.setOnClickListener {
            val deleteUser = dbh.deletedata(username)
            if(deleteUser) {
                Toast.makeText(this, "Delete Successful", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Delete Failed", Toast.LENGTH_SHORT).show()
            }

            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        btnHome.setOnClickListener {
            val intent = Intent(applicationContext, UserMainHome::class.java)
            startActivity(intent)
        }
    }
}