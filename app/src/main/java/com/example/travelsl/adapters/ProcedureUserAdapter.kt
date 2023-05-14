package com.example.travelsl.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travelsl.R
import com.example.travelsl.models.ProcedureModel

class ProcedureUserAdapter (private val ProList:ArrayList<ProcedureModel>): RecyclerView.Adapter<ProcedureUserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProcedureUserAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.procedure_activity_list_item_user, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProcedureUserAdapter.ViewHolder, position: Int) {
        val currentProcedure  = ProList[position]

        holder.tvProNameItem.text = currentProcedure.procedureName
        holder.tvProMedCenterItem.text = currentProcedure.procedureMedCenter
        holder.tvProUpperRangeItem.text = currentProcedure.procedureRangeUpper
        holder.tvProLowerRangeItem.text = currentProcedure.procedureRangeLower
    }

    override fun getItemCount(): Int {
        return ProList.size
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        val tvProNameItem : TextView = itemView.findViewById(R.id.tvProNameItem)
        val tvProMedCenterItem : TextView = itemView.findViewById(R.id.tvProMedCenterItem)
        val tvProUpperRangeItem : TextView = itemView.findViewById(R.id.tvProUpperRangeItem)
        val tvProLowerRangeItem : TextView = itemView.findViewById(R.id.tvProLowerRangeItem)
    }}