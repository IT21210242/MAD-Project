package com.example.travelsl.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.travelsl.R
import com.example.travelsl.database.TodoDatabase
import com.example.travelsl.database.entities.Todo
import com.example.travelsl.database.repositories.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodoAdapter() : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    // declare variables for the adapter
    lateinit var data: List<Todo>
    lateinit var context: Context

    // view holder class for the adapter
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // declare views in the view holder
        val cbTodo: CheckBox
        val ivDelete: ImageView

        // initialize views
        init {
            cbTodo = view.findViewById(R.id.cbTodo)
            ivDelete = view.findViewById(R.id.ivDelete)
        }
    }

    // method to set data for the adapter
    fun setData(data: List<Todo>, context: Context) {
        this.data = data
        this.context = context
        notifyDataSetChanged()
    }

    // create view holder for the adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item, parent, false)
        return ViewHolder(view)
    }

    // bind data to the view holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // set the text of the checkbox to the item from the data list
        holder.cbTodo.text = data[position].item
        // set a click listener on the delete button
        holder.ivDelete.setOnClickListener {
            // check if the checkbox is checked
            if(holder.cbTodo.isChecked){
                // create a repository object
                val repository = TodoRepository(TodoDatabase.getInstance(context))
                // change the delete button image resource
                holder.ivDelete.setImageResource(R.drawable.deletebtn)
                // launch a coroutine on the IO thread to delete the item from the database
                CoroutineScope(Dispatchers.IO).launch {
                    repository.delete(data[position])
                    // get all the todos from the database and update the data list
                    val data = repository.getAllTodos()
                    // switch to the main thread to update the UI
                    withContext(Dispatchers.Main) {
                        // update the data in the adapter
                        setData(data, context)
                        // change the delete button image resource
                        holder.ivDelete.setImageResource(R.drawable.deletebtn)
                    }
                }
            }else{
                // show a toast if the checkbox is not checked
                Toast.makeText(context,"Cannot delete unmarked Todo items",Toast.LENGTH_LONG).show()
            }
        }
    }

    // return the number of items in the data list
    override fun getItemCount() = data.size
}