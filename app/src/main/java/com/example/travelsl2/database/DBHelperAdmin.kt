package com.example.travelsl2.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.travelsl2.database.entities.AdminModel

// DBHelper class to handle SQLite database operations
class DBHelperAdmin(context: Context): SQLiteOpenHelper(context, "Admindata", null, 1) {
    // Method to create database table
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table Admindata (username TEXT primary key, password TEXT, name TEXT, email TEXT)")
    }

    // Method to upgrade database table if necessary
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop table if exists Admindata")
    }

    // Method to insert admin data into database
    fun insertdata(username: String, password: String, name: String, email: String): Boolean {
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("username", username)
        cv.put("password", password)
        cv.put("name", name)
        cv.put("email", email)
        val result = p0.insert("Admindata", null, cv)
        if(result== (-1).toLong()) {
            return false
        }
        return true
    }

    // Method to update user data in database
    fun updatedata(username: String, password: String, name: String, email: String): Boolean {
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("username", username)
        cv.put("password", password)
        cv.put("name", name)
        cv.put("email", email)
        val result = p0.update("Admindata", cv, "username='$username'", null)
        return true
    }

    // Method to delete user data from database
    fun deletedata(username: String): Boolean {
        val p0 = this.writableDatabase
        val result = p0.delete("Admindata", "username='$username'", null)
        return true
    }

    // Method to check if username and password match in database
    fun checkuserpass(username: String, password: String): Boolean {
        val p0 = this.writableDatabase
        val query = "SELECT * FROM Admindata where username='$username' and password='$password'"
        val cursor = p0.rawQuery(query, null)
        if (cursor.count <= 0) {
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }

    // Method to retrieve user details from database
    fun getUser(username: String): AdminModel {
        val p0 = this.readableDatabase
        val cursorUser: Cursor = p0.rawQuery("SELECT * FROM Admindata where username='$username'", null)
        val adminDetails: AdminModel = AdminModel()

        if (cursorUser.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                adminDetails.username = cursorUser.getString(0)
                adminDetails.name = cursorUser.getString(2)
                adminDetails.email = cursorUser.getString(3)
                adminDetails.password = cursorUser.getString(1)
            } while (cursorUser.moveToNext())
            // moving our cursor to next.
        }
        // at last closing our cursor and returning our array list.
        cursorUser.close()
        return adminDetails
    }
}