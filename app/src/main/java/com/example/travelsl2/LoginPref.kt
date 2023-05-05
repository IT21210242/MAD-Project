package com.example.travelsl2

import android.content.Context
import android.content.SharedPreferences

class LoginPref {
    lateinit var pref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var con: Context
    var PRIVATEMODE: Int = 0

    constructor(con: Context) {
        this.con = con
        pref = con.getSharedPreferences(PREF_NAME, PRIVATEMODE)
        editor = pref.edit()
    }

    companion object{
        val PREF_NAME = "Login_Preferences"
        val IS_LOGIN = "isLoggedIn"
        var KEY_USERNAME = "username"
    }

    fun createLoginSession(username: String){
        editor.putBoolean(IS_LOGIN, true)
        KEY_USERNAME = username
        editor.putString(KEY_USERNAME, username)
        println(KEY_USERNAME)
        println(username)
        editor.commit()
    }

    fun getUserDetails(): String {
        return KEY_USERNAME
    }
}