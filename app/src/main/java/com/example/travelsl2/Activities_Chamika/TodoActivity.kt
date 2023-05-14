package com.example.travelsl2.Activities_Chamika


import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travelsl2.R
import com.example.travelsl2.adapters.TodoAdapter
import com.example.travelsl2.database.TodoDatabase
import com.example.travelsl2.database.entities.Todo
import com.example.travelsl2.database.repositories.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
 This is the TodoActivity class that handles the display of a list of todos
 and the addition of new todos to the list.
 */
class TodoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        // Get the views for the bottom navigation bar
        val btnHome: ImageView = findViewById(R.id.btnhome3)
        val btnSearch: ImageView = findViewById(R.id.btnsearch3)
        val btnHealth: ImageView = findViewById(R.id.btnhealth3)
        val btnDashboard: ImageView = findViewById(R.id.btndashboard3)

        // Set click listeners for the bottom navigation bar buttons
        btnHome.setOnClickListener {
            val intent = Intent(applicationContext, UserMainHome::class.java)
            startActivity(intent)

        }

        btnSearch.setOnClickListener {

        }

        btnHealth.setOnClickListener {

        }

        btnDashboard.setOnClickListener {
            val intent = Intent(applicationContext, Dashboard::class.java)
            startActivity(intent)
        }

        // Set up the RecyclerView and adapter for the list of todos
        val repository = TodoRepository(TodoDatabase.getInstance(this))
        val recyclerView: RecyclerView = findViewById(R.id.rvTodoList)
        val ui = this

        val adapter = TodoAdapter()

        // Get all the todos from the database and set the data in the adapter
        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getAllTodos()
            adapter.setData(data, ui)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(ui)

        // Set up the button to display the add todos dialog
        val btnAddTodo = findViewById<Button>(R.id.btnAddTodo)
        btnAddTodo.setOnClickListener {
            displayDialog(repository, adapter)
        }
    }

    private fun displayDialog(repository: TodoRepository, adapter: TodoAdapter) {
        // Create a new instance of AlertDialog.Builder
        val builder = AlertDialog.Builder(this)

        // Set the alert dialog title and message
        builder.setTitle("Enter New item to Planner:")
        builder.setMessage("Enter the new item below:")

        // Create an EditText input field
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        // Set the positive button action
        builder.setPositiveButton("OK") { dialog, which ->

            // Get the input text and display a Toast message
            val item = input.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                repository.insert(Todo(item))
                val data = repository.getAllTodos()
                runOnUiThread {
                    adapter.setData(data, this@TodoActivity)
                }
            }
        }

        // Set the negative button action
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }

        // Create and show the alert dialog
        val alertDialog = builder.create()
        alertDialog.show()
    }
}