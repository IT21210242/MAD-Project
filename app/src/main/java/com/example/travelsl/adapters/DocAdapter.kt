package com.example.travelsl.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.travelsl.R
import com.example.travelsl.models.DoctorModel
import com.google.firebase.database.FirebaseDatabase

class DocAdapter (private var DocList:ArrayList<DoctorModel>): RecyclerView.Adapter<DocAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.doctor_activity_list_item, parent, false)
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: DocAdapter.ViewHolder, position: Int) {
        val currentDoc  = DocList[position]

        holder.tvDocName.text = currentDoc.doctorName
        holder.tvDocSpec.text = currentDoc.doctorSpec
        holder.tvDocMedCenter.text = currentDoc.doctorMedCenter
        holder.tvDocTel.text = currentDoc.doctorTel
        holder.tvDocEmail.text = currentDoc.doctorEmail

        var medType = currentDoc.doctorMedType

        holder.btnDeleteDocRecord.setOnClickListener {
//            val deletedData = DocList[position]
//            FirebaseDatabase.getInstance().getReference("Doctors").child(deletedData.doctorId.toString()).removeValue()
            val context = holder.itemView.context
            val alertDialog = AlertDialog.Builder(context)
                .setTitle("Confirm delete")
                .setMessage("Are you sure you want to delete this item?")
                .setPositiveButton("Delete") { _, _ ->
                    val deletedData = DocList[position]
                    FirebaseDatabase.getInstance().getReference("Doctors").child(deletedData.doctorId.toString()).removeValue()
                    notifyItemRemoved(position)
                    Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancel", null)
                .create()
            alertDialog.show()
        }
        holder.btnUpdateDocRecord.setOnClickListener {

            println(currentDoc.doctorId.toString())
            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setTitle("Update Item")

            val editTextDocName = EditText(holder.itemView.context)
            val editTextDocSpec = EditText(holder.itemView.context)
            val editTextDocMedCenter = EditText(holder.itemView.context)
            val editTextDocTel = EditText(holder.itemView.context)
            val editTextDocEmail = EditText(holder.itemView.context)
            val docId = currentDoc.doctorId.toString()

            editTextDocName.setText(currentDoc.doctorName)
            editTextDocSpec.setText(currentDoc.doctorSpec)
            editTextDocMedCenter.setText(currentDoc.doctorMedCenter)
            editTextDocTel.setText(currentDoc.doctorTel)
            editTextDocEmail.setText(currentDoc.doctorEmail)

            builder.setView(
                LinearLayout(holder.itemView.context).apply {
                    orientation = LinearLayout.VERTICAL
                    addView(editTextDocName)
                    addView(editTextDocSpec)
                    addView(editTextDocMedCenter)
                    addView(editTextDocTel)
                    addView(editTextDocEmail)
                }
            )
            builder.setPositiveButton("Update") { _, _ ->
                val docName = editTextDocName.text.toString()
                val docSpec = editTextDocSpec.text.toString()
                val docMedCenter = editTextDocMedCenter.text.toString()
                val docTel = editTextDocTel.text.toString()
                val docEmail = editTextDocEmail.text.toString()
                updateDocData(
                    docId,docName,docSpec,docMedCenter,docTel,docEmail,medType
                )
            }
            builder.setNegativeButton("Cancel", null)
            builder.show()
        }
    }
    override fun getItemCount(): Int {
        return DocList.size
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        val tvDocName : TextView = itemView.findViewById(R.id.tvDocNameItem)
        val tvDocSpec : TextView = itemView.findViewById(R.id.tvDocMedSpecItem)
        val tvDocMedCenter : TextView = itemView.findViewById(R.id.tvDocMedCenterItem)
        val tvDocTel : TextView = itemView.findViewById(R.id.tvDocTelephoneItem)
        val tvDocEmail : TextView = itemView.findViewById(R.id.tvDocEmailItem)
        val btnDeleteDocRecord : Button = itemView.findViewById(R.id.btnDeleteDocRecord)
        val btnUpdateDocRecord : Button = itemView.findViewById(R.id.btnUpdateDocRecord)
    }

    private fun updateDocData(
        id: String?,
        name: String?,
        spec: String?,
        medCenter: String?,
        tel: String?,
        email: String?,
        medType: String?
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Doctors").child(id.toString())
        val docInfo = DoctorModel(doctorId = id, doctorName = name, doctorSpec =  spec,
            doctorMedCenter =  medCenter, doctorTel = tel, doctorEmail = email, doctorMedType = medType)
        if (dbRef != null) {
            dbRef.setValue(docInfo)
            println(id)
        }
        else{
            println("bdbcbcbcbcvbcvbfvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv")
        }
    }
}

