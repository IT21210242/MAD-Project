package com.example.travelsl2.session

import android.content.Context
import android.content.SharedPreferences

// Define a class to manage user login preferences
class LoginPref {
    // Declare instance variables
    lateinit var pref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var con: Context
    var PRIVATEMODE: Int = 0

    // Define the primary constructor for this class
    constructor(con: Context) {
        // Initialize the instance variables
        this.con = con
        pref = con.getSharedPreferences(PREF_NAME, PRIVATEMODE)
        editor = pref.edit()
    }

    companion object{
        // Declare constant values for login preferences
        val PREF_NAME = "Login_Preferences"
        val IS_LOGIN = "isLoggedIn"
        var KEY_USERNAME = "username"
    }

    // Define a method to create a new login session
    fun createLoginSession(username: String){
        editor.putBoolean(IS_LOGIN, true)
        KEY_USERNAME = username
        editor.putString(KEY_USERNAME, username)
        println(KEY_USERNAME)
        println(username)
        editor.commit()
    }

    // Define a method to retrieve user details
    fun getUserDetails(): String {
        return KEY_USERNAME
    }
}