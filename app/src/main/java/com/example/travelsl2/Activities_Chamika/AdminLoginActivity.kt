package com.example.travelsl2.Activities_Chamika

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.travelsl2.R
import com.example.travelsl2.database.DBHelperAdmin
import com.example.travelsl2.session.LoginPref

class AdminLoginActivity : AppCompatActivity() {
    // Declare instance variables
    private lateinit var btnlogin: Button
    private lateinit var uname: EditText
    private lateinit var pwd: EditText
    private lateinit var dbh: DBHelperAdmin

    lateinit var session: LoginPref

    // Define the activity's onCreate method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_login)

        // Initialize instance variables
        session = LoginPref(this)

        btnlogin = findViewById(R.id.btnlogin)
        uname = findViewById(R.id.txtUsername)
        pwd = findViewById(R.id.txtPassword)
        dbh = DBHelperAdmin(this)

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
                    val intent = Intent(applicationContext, AdminDashboardActivity::class.java)
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