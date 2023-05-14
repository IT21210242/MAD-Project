package com.example.travelsl.activities_chamika

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.travelsl.R
import com.example.travelsl.database.DBHelper
import com.example.travelsl.session.LoginPref

// Define the main activity class
class MainActivity : AppCompatActivity() {
    // Declare instance variables
    private lateinit var btnsignup: Button

    private lateinit var btnlogin: Button
    private lateinit var uname: EditText
    private lateinit var pwd: EditText
    private lateinit var dbh: DBHelper
    private lateinit var adminlogin: Button

    lateinit var session: LoginPref

    // Define the activity's onCreate method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // Initialize instance variables
        session = LoginPref(this)

        btnsignup = findViewById(R.id.signup)

        // Set click listener for the "Sign Up" button
        btnsignup.setOnClickListener {
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        adminlogin = findViewById(R.id.adminlogin)
        //Go to admin login page
        adminlogin.setOnClickListener {
            var intent = Intent(this, AdminLoginActivity::class.java)
            startActivity(intent)
        }

        btnlogin = findViewById(R.id.btnlogin)
        uname = findViewById(R.id.txtUsername)
        pwd = findViewById(R.id.txtPassword)
        dbh = DBHelper(this)

        // Set click listener for the "Log In" button
        btnlogin.setOnClickListener {
            val unametxt = uname.text.toString()
            val pwdtxt = pwd.text.toString()

            // Check if the user has provided both a username and password
            if (TextUtils.isEmpty(unametxt) || TextUtils.isEmpty(pwdtxt)) {
                Toast.makeText(this, "Username or Password missing", Toast.LENGTH_SHORT).show()
            }
            else {
                // Check if the provided username and password are valid
                val checkuser = dbh.checkuserpass(unametxt, pwdtxt)
                if(checkuser) {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                    // Create a login session for the user
                    session.createLoginSession(unametxt)
                    println(unametxt)
                    println(session.getUserDetails())

                    // Launch the main user interface activity
                    val intent = Intent(applicationContext, UserMainHome::class.java)
                    startActivity(intent)
                }
                else {
                    // Notify the user that the login attempt failed
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}