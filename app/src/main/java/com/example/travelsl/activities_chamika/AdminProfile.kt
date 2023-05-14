package com.example.travelsl.activities_chamika

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.travelsl.R
import com.example.travelsl.database.DBHelperAdmin
import com.example.travelsl.database.entities.AdminModel
import com.example.travelsl.session.LoginPref

class AdminProfile : AppCompatActivity() {
    lateinit var session: LoginPref
    private lateinit var dbh: DBHelperAdmin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_profile)

        // Initialize UI elements
        val txtuname = findViewById<TextView>(R.id.updateuname)
        val txtname = findViewById<TextView>(R.id.updatename)
        val txtemail = findViewById<TextView>(R.id.updateEmail)
        val txtpwd = findViewById<TextView>(R.id.updatepwdtxt2)
        val updatebtn = findViewById<Button>(R.id.updateprofile)
        val deletebtn = findViewById<Button>(R.id.delete)

        val btnHome: ImageView = findViewById(R.id.backbtn1)

        // Initialize session and database helper
        dbh = DBHelperAdmin(this)
        session = LoginPref(this)
        var username: String = session.getUserDetails()
        println(username)
        val admindetails: AdminModel = dbh.getUser(username)

        // Get user details from database
        val uname: String = admindetails.username
        val name: String = admindetails.name
        val email: String = admindetails.email
        val password: String = admindetails.password

        // Set user details in UI elements
        txtuname?.setText(uname, null)
        txtname?.setText(name, null)
        txtemail?.setText(email, null)
        txtpwd?.setText(password, null)

        // Update user profile button click listener
        updatebtn.setOnClickListener {
            val nameupdate = txtname.text.toString()
            val emailupdate = txtemail.text.toString()
            val pwdupdate = txtpwd.text.toString()

            // Update user profile data in database
            val updateUser = dbh.updatedata(uname, pwdupdate, nameupdate, emailupdate)

            // Show toast message based on update result
            if(updateUser) {
                Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show()
            }

            // Reload the activity to show updated user details
            val intent = Intent(applicationContext, AdminProfile::class.java)
            startActivity(intent)
        }

        // Delete user account button click listener
        deletebtn.setOnClickListener {
            // Delete user account from database
            val deleteUser = dbh.deletedata(username)

            // Show toast message based on delete result
            if(deleteUser) {
                Toast.makeText(this, "Delete Successful", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Delete Failed", Toast.LENGTH_SHORT).show()
            }

            // Redirect to main activity after successful deletion
            val intent = Intent(applicationContext, AdminLoginActivity::class.java)
            startActivity(intent)
        }

        // Back button click listener to go back to dashboard
        btnHome.setOnClickListener {
            val intent = Intent(applicationContext, AdminDashboardActivity::class.java)
            startActivity(intent)
        }
    }
}