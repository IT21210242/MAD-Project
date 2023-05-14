package com.example.travelsl.activities_tharindu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travelsl.R
import com.example.travelsl.adapters.DocUserAdapter
import com.example.travelsl.models.DoctorModel
import com.google.firebase.database.*

class DoctorFetchAyurvedicUserActivity : AppCompatActivity() {

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
        setContentView(R.layout.doctor_activity_fetch_ayurvedic_user)

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
            val intent = Intent(this, DoctorFetchWesternUserActivity::class.java)
            startActivity(intent)
        }
        getDoctorData()

    }

    private fun getDoctorData() {
        docRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE
        searchBarDoc.visibility = View.GONE
        buttonSet.visibility = View.GONE

        dbRef = FirebaseDatabase.getInstance().getReference("Doctors")

        val query = dbRef.orderByChild("doctorMedType").equalTo("Ayurveda Medicine")

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                docList.clear()
                if (snapshot.exists()) {
                    for (docSnap in snapshot.children) {
                        val docData = docSnap.getValue(DoctorModel::class.java)
                        docList.add(docData!!)
                    }
                    tempResultList.addAll(docList)
                    val mAdapter = DocUserAdapter(docList)
                    docRecyclerView.adapter = mAdapter

                    docRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                    searchBarDoc.visibility = View.VISIBLE
                    buttonSet.visibility = View.VISIBLE
                } else {
                    val intent = Intent(
                        this@DoctorFetchAyurvedicUserActivity,
                        UserSelectMedActivity::class.java
                    )
                    startActivity(intent)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }
}