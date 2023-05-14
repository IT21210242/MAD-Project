package com.example.travelsl.activities_chanithi

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.travelsl.databinding.ActivityMainBinding
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList
import com.example.travelsl.R

//main activity includes all the main activities related to new place upload, search, update and delete

class MainActivityChanithi : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var dataList: ArrayList<DataClass>
    private lateinit var adapter: MyAdapter
    var databaseReference: DatabaseReference? = null
    var eventListener: ValueEventListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gridLayoutManager = GridLayoutManager(this@MainActivityChanithi, 1)
        binding.recyclerView.layoutManager = gridLayoutManager

        val builder = AlertDialog.Builder(this@MainActivityChanithi)
        builder.setCancelable(false)
        builder.setView(R.layout.progress_layout)
        val dialog = builder.create()
        dialog.show()

        dataList = ArrayList()
        adapter = MyAdapter(this@MainActivityChanithi, dataList)
        binding.recyclerView.adapter = adapter
        databaseReference = FirebaseDatabase.getInstance().getReference("Most Recommended")
        dialog.show()

        eventListener = databaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (itemSnapshot in snapshot.children) {
                    val dataClass = itemSnapshot.getValue(DataClass::class.java)
                    if (dataClass != null) {
                        dataList.add(dataClass)
                    }
                }
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                dialog.dismiss()
            }
        })


        binding.fab.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivityChanithi, Uploadactivity::class.java)
            startActivity(intent)
        })

        binding.fab2.setOnClickListener{
            val intent2 = Intent(this@MainActivityChanithi, eveMainActivity::class.java)
            startActivity(intent2)
        }

        binding.fab3.setOnClickListener{
            val intent3 = Intent(this@MainActivityChanithi, UpdateActivity::class.java)
            startActivity(intent3)
        }

        binding.fab4.setOnClickListener{
            val intent4 = Intent(this@MainActivityChanithi, DeleteActivity::class.java)
            startActivity(intent4)
        }


        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchList(newText)
                return true
            }

        })

    }

    fun searchList(text: String) {
        val searchList = ArrayList<DataClass>()
        for (dataClass in dataList) {
            if (dataClass.dataPriority?.lowercase()?.contains(text.lowercase()) == true) {
                searchList.add(dataClass)
            }
            if (dataClass.dataTitle?.lowercase()?.contains(text.lowercase()) == true) {
                searchList.add(dataClass)
            }
        }
        adapter.searchDataList(searchList)
    }
}