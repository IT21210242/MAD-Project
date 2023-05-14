package com.example.travelsl.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travelsl.R
import com.example.travelsl.models.DoctorModel

class DocUserAdapter(private val DocList:ArrayList<DoctorModel>): RecyclerView.Adapter<DocUserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocUserAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.doctor_activity_list_item_user, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DocUserAdapter.ViewHolder, position: Int) {
        val currentDoc  = DocList[position]

        holder.tvDocName.text = currentDoc.doctorName
        holder.tvDocSpec.text = currentDoc.doctorSpec
        holder.tvDocMedCenter.text = currentDoc.doctorMedCenter
        holder.tvDocTel.text = currentDoc.doctorTel
        holder.tvDocEmail.text = currentDoc.doctorEmail
    }

    override fun getItemCount(): Int {
        return DocList.size
    }

    class ViewHolder(v: View):RecyclerView.ViewHolder(v){
        val tvDocName : TextView = itemView.findViewById(R.id.tvDocNameItem)
        val tvDocSpec : TextView = itemView.findViewById(R.id.tvDocMedSpecItem)
        val tvDocMedCenter : TextView = itemView.findViewById(R.id.tvDocMedCenterItem)
        val tvDocTel : TextView = itemView.findViewById(R.id.tvDocTelephoneItem)
        val tvDocEmail : TextView = itemView.findViewById(R.id.tvDocEmailItem)
    }
}