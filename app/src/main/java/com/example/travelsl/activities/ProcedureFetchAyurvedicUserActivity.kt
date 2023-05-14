package com.example.travelsl.activities

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
import com.example.travelsl.adapters.ProcedureUserAdapter
import com.example.travelsl.models.ProcedureModel
import com.google.firebase.database.*

class ProcedureFetchAyurvedicUserActivity : AppCompatActivity() {

    private lateinit var proRecyclerView: RecyclerView
    private lateinit var tvLoadingData : TextView
    private lateinit var searchBarPro: SearchView
    private lateinit var buttonSet: LinearLayout
    private lateinit var btnWeMedicine: Button

    private lateinit var dbRef: DatabaseReference

    private lateinit var proList: ArrayList<ProcedureModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.procedure_activity_fetch_ayurvedic_user)

        proRecyclerView = findViewById(R.id.rvPro)
        proRecyclerView.layoutManager = LinearLayoutManager(this)
        proRecyclerView.setHasFixedSize(true)

        tvLoadingData = findViewById(R.id.tvLoadingData)
        searchBarPro = findViewById(R.id.searchBarPro)
        buttonSet = findViewById(R.id.buttonSet)
        btnWeMedicine = findViewById(R.id.btnWeMedicine)

        proList = arrayListOf<ProcedureModel>()

        btnWeMedicine.setOnClickListener {
            val intent = Intent(this, ProcedureFetchWesternUserActivity::class.java)
            startActivity(intent)
        }
        getProcedureData()
    }

    private fun getProcedureData() {
        proRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE
        searchBarPro.visibility = View.GONE
        buttonSet.visibility = View.GONE

        dbRef = FirebaseDatabase.getInstance().getReference("Procedures")

        val query = dbRef.orderByChild("procedureMedType").equalTo("Ayurveda Medicine")
        query.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                proList.clear()
                if (snapshot.exists()){
                    for (docSnap in snapshot.children){
                        val proData = docSnap.getValue(ProcedureModel::class.java)
                        proList.add(proData!!)
                    }
                    val mAdapter = ProcedureUserAdapter(proList)
                    proRecyclerView.adapter=mAdapter

                    proRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                    searchBarPro.visibility = View.VISIBLE
                    buttonSet.visibility = View.VISIBLE
                }
                else{
                    val intent = Intent(this@ProcedureFetchAyurvedicUserActivity, UserSelectMedActivity::class.java);
                    startActivity(intent);
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}