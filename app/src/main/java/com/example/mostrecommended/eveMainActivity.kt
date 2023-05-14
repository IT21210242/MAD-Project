package com.example.mostrecommended

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mostrecommended.databinding.EveactivityMainBinding
import com.google.firebase.database.*

//include all the main activities regarding to event calendar

class eveMainActivity : AppCompatActivity() {

    private lateinit var binding: EveactivityMainBinding
    private lateinit var dataList: ArrayList<eveDataClass>
    private lateinit var adapter: evemyAdapter
    var databaseReference: DatabaseReference? = null
    var eventListener: ValueEventListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EveactivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gridLayoutManager2 = GridLayoutManager(this@eveMainActivity, 1)
        binding.recyclerView.layoutManager = gridLayoutManager2

        val builder2 = AlertDialog.Builder(this@eveMainActivity)
        builder2.setCancelable(false)
        builder2.setView(R.layout.eveprogress_layout)
        val dialog = builder2.create()
        dialog.show()

        dataList = ArrayList()
        adapter = evemyAdapter(this@eveMainActivity, dataList)
        binding.recyclerView.adapter = adapter
        databaseReference = FirebaseDatabase.getInstance().getReference("eventCalendar")

        eventListener = databaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (itemSnapshot in snapshot.children) {
                    val dataClass2 = itemSnapshot.getValue(eveDataClass::class.java)
                    if (dataClass2 != null) {
                        dataList.add(dataClass2)
                    }
                }
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                dialog.dismiss()
            }

        })

        //link the add button with the eveUploadactivity page
        binding.fab.setOnClickListener {
            val intent = Intent(this, eveUploadactivity::class.java)
            startActivity(intent)
        }


    }
}