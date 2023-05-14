package com.example.travelsl.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.travelsl.database.daos.TodoDao
import com.example.travelsl.database.entities.Todo

// Indicates that this class represents a Room database with a single entity (Todos) and a version number of 1
@Database(entities = [Todo::class], version = 1)

// An abstract class that extends RoomDatabase, which is used to define the database and its entities
abstract class TodoDatabase: RoomDatabase(){

    // An abstract method that returns an instance of the TodoDao interface, which is used to perform CRUD operations on the Todos entity in the database
    abstract fun getTodoDao(): TodoDao
    companion object{
        // A private nullable volatile variable that holds a reference to the TodoDatabase instance
        @Volatile
        private var INSTANCE: TodoDatabase? = null
        fun getInstance(context: Context): TodoDatabase {
            // A static method that returns a TodoDatabase instance
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}