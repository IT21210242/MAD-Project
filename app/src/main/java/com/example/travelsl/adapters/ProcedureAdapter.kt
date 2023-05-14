package com.example.travelsl.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.travelsl.R
import com.example.travelsl.models.ProcedureModel
import com.google.firebase.database.FirebaseDatabase

class ProcedureAdapter(private var ProList:ArrayList<ProcedureModel>):RecyclerView.Adapter<ProcedureAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProcedureAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.procedure_activity_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProcedureAdapter.ViewHolder, position: Int) {
        val currentProcedure = ProList[position]

        holder.tvProNameItem.text = currentProcedure.procedureName
        holder.tvProMedCenterItem.text = currentProcedure.procedureMedCenter
        holder.tvProUpperRangeItem.text = currentProcedure.procedureRangeUpper
        holder.tvProLowerRangeItem.text = currentProcedure.procedureRangeLower

        var medType = currentProcedure.procedureMedType

        holder.btnDeleteProcedureRecord.setOnClickListener {
//            val deletedData = DocList[position]
//            FirebaseDatabase.getInstance().getReference("Doctors").child(deletedData.doctorId.toString()).removeValue()
            val context = holder.itemView.context
            val alertDialog = AlertDialog.Builder(context)
                .setTitle("Confirm delete")
                .setMessage("Are you sure you want to delete this item?")
                .setPositiveButton("Delete") { _, _ ->
                    val deletedData = ProList[position]
                    FirebaseDatabase.getInstance().getReference("Doctors").child(deletedData.procedureId.toString()).removeValue()
                    notifyItemRemoved(position)
                    Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancel", null)
                .create()
            alertDialog.show()
        }

        holder.btnUpdateProcedureRecord.setOnClickListener{

            println(currentProcedure.procedureId.toString())
            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setTitle("Update Item")

            val editTexProName = EditText(holder.itemView.context)
            val editTextProMedCenter = EditText(holder.itemView.context)
            val editTextProRangeUpper = EditText(holder.itemView.context)
            val editTextProRangeLower = EditText(holder.itemView.context)
            val proId = currentProcedure.procedureId.toString()

            editTexProName.setText(currentProcedure.procedureName)
            editTextProMedCenter.setText(currentProcedure.procedureMedCenter)
            editTextProRangeUpper.setText(currentProcedure.procedureRangeUpper)
            editTextProRangeLower.setText(currentProcedure.procedureRangeLower)

            builder.setView(
                LinearLayout(holder.itemView.context).apply {
                    orientation = LinearLayout.VERTICAL
                    addView(editTexProName)
                    addView(editTextProMedCenter)
                    addView(editTextProRangeUpper)
                    addView(editTextProRangeLower)
                }
            )
            builder.setPositiveButton("Update") { _, _ ->
                val proName = editTexProName.text.toString()
                val proMedCenter = editTextProMedCenter.text.toString()
                val proRangeUpper = editTextProRangeUpper.text.toString()
                val proRangeLower = editTextProRangeLower.text.toString()
                updateProData(
                    proId,proName,proMedCenter,proRangeUpper,proRangeLower,medType
                )
            }
            builder.setNegativeButton("Cancel", null)
            builder.show()
        }

    }

    private fun updateProData(
        proId: String,
        proName: String,
        proMedCenter: String,
        proRangeUpper: String,
        proRangeLower: String,
        medType: String?
    ) {

        val dbRef = FirebaseDatabase.getInstance().getReference("Procedures").child(proId.toString())
        val docInfo = ProcedureModel( procedureId=proId, procedureName = proName, procedureMedCenter =  proMedCenter,
            procedureRangeUpper =  proRangeUpper, procedureRangeLower = proRangeLower, procedureMedType = medType)
        if (dbRef != null) {
            dbRef.setValue(docInfo)
            println(proId)
        }
        else{
            println("Else")
        }
    }

    override fun getItemCount(): Int {
        return ProList.size
    }
    class ViewHolder(v:View):RecyclerView.ViewHolder(v){
        val tvProNameItem : TextView = itemView.findViewById(R.id.tvProNameItem)
        val tvProMedCenterItem : TextView = itemView.findViewById(R.id.tvProMedCenterItem)
        val tvProUpperRangeItem : TextView = itemView.findViewById(R.id.tvProUpperRangeItem)
        val tvProLowerRangeItem : TextView = itemView.findViewById(R.id.tvProLowerRangeItem)
        val btnUpdateProcedureRecord : TextView = itemView.findViewById(R.id.btnUpdateProcedureRecord)
        val btnDeleteProcedureRecord : Button = itemView.findViewById(R.id.btnDeleteProcedureRecord)
    }
}