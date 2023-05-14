package com.example.travelsl.activities_chamika

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.travelsl.R
import com.example.travelsl.database.DBHelperAdmin

class AdminRegisterActivity : AppCompatActivity() {

    // Declaring variables for the UI elements of the activity
    private lateinit var uname: EditText
    private lateinit var pwd: EditText
    private lateinit var name: EditText
    private lateinit var email: EditText
    private  lateinit var signupbtn: Button
    private lateinit var cancelbtn: Button
    private lateinit var db: DBHelperAdmin

    // The onCreate method is called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Setting the layout of the activity using the setContentView method
        setContentView(R.layout.admin_register)

        // Getting references to the UI elements of the activity using their IDs
        uname = findViewById(R.id.txtUsername)
        pwd = findViewById(R.id.txtPassword)
        name = findViewById(R.id.txtname)
        email = findViewById(R.id.txtEmail)
        signupbtn = findViewById(R.id.btnlogin)
        cancelbtn = findViewById(R.id.btncancel)
        db = DBHelperAdmin(this)

        // Setting a click listener for the Signup button to insert user data into the database
        signupbtn.setOnClickListener {
            // Retrieving the user input from the EditText views
            val unametxt = uname.text.toString()
            val pwdtxt = pwd.text.toString()
            val nametxt = name.text.toString()
            val emailtxt = email.text.toString()
            // Inserting the user data into the database using the insertdata method of the DBHelper class
            val savedata = db.insertdata(unametxt, pwdtxt, nametxt, emailtxt)

            // Validating the user input and showing a toast message accordingly
            if (TextUtils.isEmpty(unametxt) || TextUtils.isEmpty(pwdtxt) || TextUtils.isEmpty(nametxt) || TextUtils.isEmpty(emailtxt)) {
                Toast.makeText(this, "Add username, email, name or password", Toast.LENGTH_SHORT).show()
            }
            else {
                if(savedata) {
                    Toast.makeText(this, "Signup Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, AdminDashboardActivity::class.java)
                    startActivity(intent)
                }
                else {
                    Toast.makeText(this, "Signup Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

        cancelbtn.setOnClickListener {
            val intent = Intent(applicationContext, AdminDashboardActivity::class.java)
            startActivity(intent)
        }
    }
}