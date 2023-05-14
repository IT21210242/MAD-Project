package com.example.travelsl.activities_tharindu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.travelsl.R
import com.example.travelsl.models.ProcedureModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProcedureInsertActivity : AppCompatActivity() {

    private lateinit var etProcedureName:EditText
    private lateinit var etProcedureMedCenter:EditText
    private lateinit var etProcedureUpperPrice:EditText
    private lateinit var etProcedureLowerPrice:EditText
    private lateinit var spnProcedureMedType:Spinner
    private lateinit var btnSaveEntry: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.procedure_activity_insert)

        etProcedureName = findViewById(R.id.etProcedureName)
        etProcedureMedCenter = findViewById(R.id.etProcedureMedCenter)
        etProcedureUpperPrice = findViewById(R.id.etProcedureUpperPriceRange)
        etProcedureLowerPrice = findViewById(R.id.etProcedureLowerPriceRange)
        spnProcedureMedType = findViewById(R.id.spnProcedureMedType)

        btnSaveEntry = findViewById(R.id.btnSubmitProcedureEntry)
        dbRef = FirebaseDatabase.getInstance().getReference("Procedures")

        btnSaveEntry.setOnClickListener {
            saveProcedureData()
        }
    }

    private fun saveProcedureData(){
        val procedureName = etProcedureName.text.toString()
        val procedureMedCenter = etProcedureMedCenter.text.toString()
        val procedureUpperPrice = etProcedureUpperPrice.text.toString()
        val procedureLowerPrice = etProcedureLowerPrice.text.toString()
        val procedureMedType = spnProcedureMedType.selectedItem.toString()

        if (procedureName.isEmpty()) {
            etProcedureName.error = "Please enter name"
        }
        if (procedureMedCenter.isEmpty()) {
            etProcedureMedCenter.error = "Please enter specialisation"
        }
        if (procedureUpperPrice.isEmpty()) {
            etProcedureUpperPrice.error = "Please enter medical center"
        }
        if (procedureLowerPrice.isEmpty()) {
            etProcedureLowerPrice.error = "Please enter telephone number"
        }

        val procedureId = dbRef.push().key.toString()

        val procedureDb = ProcedureModel(procedureId,
            procedureName,procedureMedCenter,procedureMedType,procedureUpperPrice,procedureLowerPrice)

        dbRef.child(procedureId).setValue(procedureDb).addOnCompleteListener{
            Toast.makeText(this,"Data Inserted successfully", Toast.LENGTH_LONG).show()
            etProcedureName.text.clear()
            etProcedureMedCenter.text.clear()
            etProcedureUpperPrice.text.clear()
            etProcedureLowerPrice.text.clear()
        }.addOnFailureListener{ err->
            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
        }
    }

}