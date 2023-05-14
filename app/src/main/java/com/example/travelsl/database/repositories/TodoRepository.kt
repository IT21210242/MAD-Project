package com.example.travelsl.database.repositories

import com.example.travelsl.database.TodoDatabase
import com.example.travelsl.database.entities.Todo

class TodoRepository(
    // the TodoDatabase instance used by this repository
    private val db: TodoDatabase
) {
    // inserts the given todos item into the database using the TodoDao
    suspend fun insert(todo: Todo) = db.getTodoDao().insertTodo(todo)

    // deletes the given todos item from the database using the TodoDao
    suspend fun delete(todo: Todo) = db.getTodoDao().delete(todo)

    // retrieves a list of all todos items from the database using the TodoDao
    fun getAllTodos() =db.getTodoDao().getAllTodos()
}