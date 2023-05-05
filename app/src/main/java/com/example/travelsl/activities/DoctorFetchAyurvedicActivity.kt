package com.example.travelsl.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travelsl.R
import com.example.travelsl.adapters.DocAdapter
import com.example.travelsl.models.DoctorModel
import com.google.firebase.database.*
import kotlin.collections.ArrayList

class DoctorFetchAyurvedicActivity : AppCompatActivity() {

    private lateinit var docRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var searchBarDoc: SearchView
    private lateinit var buttonSet: LinearLayout
    private lateinit var btnWeMedicine: Button

    private lateinit var dbRef: DatabaseReference

    private lateinit var docList: ArrayList<DoctorModel>
    private lateinit var searchResultList: ArrayList<DoctorModel>
    private lateinit var tempResultList: ArrayList<DoctorModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.doctor_activity_fetch_ayurvedic)

        docRecyclerView = findViewById(R.id.rvDoc)
        docRecyclerView.layoutManager = LinearLayoutManager(this)
        docRecyclerView.setHasFixedSize(true)

        tvLoadingData = findViewById(R.id.tvLoadingData)
        searchBarDoc = findViewById(R.id.searchBarDoc)
        buttonSet = findViewById(R.id.buttonSet)
        btnWeMedicine = findViewById(R.id.btnWeMedicine)

        docList = arrayListOf<DoctorModel>()
        searchResultList = arrayListOf<DoctorModel>()
        tempResultList = arrayListOf<DoctorModel>()

        btnWeMedicine.setOnClickListener {
            val intent = Intent(this, DoctorFetchWesternActivity::class.java)
            startActivity(intent)
        }

        getDoctorData()
        onCreateOptionsMenu()
    }

    private fun getDoctorData() {
        docRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE
        searchBarDoc.visibility = View.GONE
        buttonSet.visibility = View.GONE

        dbRef = FirebaseDatabase.getInstance().getReference("Doctors")

        val query = dbRef.orderByChild("doctorMedType").equalTo("Ayurveda Medicine")

        query.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                docList.clear()
                if (snapshot.exists()){
                    for (docSnap in snapshot.children){
                        val docData = docSnap.getValue(DoctorModel::class.java)
                        docList.add(docData!!)
                    }
                    tempResultList.addAll(docList)
                    val mAdapter = DocAdapter(docList)
                    docRecyclerView.adapter=mAdapter

                    docRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                    searchBarDoc.visibility = View.VISIBLE
                    buttonSet.visibility = View.VISIBLE
                }
                else{
                    val intent = Intent(this@DoctorFetchAyurvedicActivity, DoctorAdminInsertFetch::class.java)
                    startActivity(intent)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun onCreateOptionsMenu(){


        searchBarDoc.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchResultList.clear()
                if(newText!=null){
                    for (i in docList){
                        if (i.doctorName.toString().lowercase().contains(newText)){
                            searchResultList.add(i)
                        }
                    }
                    if(searchResultList.isEmpty()){
                        Toast.makeText(this@DoctorFetchAyurvedicActivity,"No Data found", Toast.LENGTH_SHORT).show()
                    }else{
                        docList.clear()
                        val adapter1 = DocAdapter(docList)
                        docRecyclerView.adapter=adapter1
                        val adapter = DocAdapter(searchResultList)
                        docRecyclerView.adapter=adapter

                    }
                }else{
                    val adapter2 = DocAdapter(tempResultList)
                    docRecyclerView.adapter=adapter2
                }

                return true
            }

        })
    }

}