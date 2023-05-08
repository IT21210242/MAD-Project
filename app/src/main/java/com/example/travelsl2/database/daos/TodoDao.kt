package com.example.travelsl2.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.travelsl2.database.entities.Todo

@Dao
interface TodoDao {
    // insert a todos into the database
    @Insert
    suspend fun insertTodo(todo: Todo)

    // delete a todos from the database
    @Delete
    suspend fun delete(todo: Todo)

    // get all todos from the database
    @Query("SELECT * From Todo")
    fun getAllTodos(): List<Todo>
}