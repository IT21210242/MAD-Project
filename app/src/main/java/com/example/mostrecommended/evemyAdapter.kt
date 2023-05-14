package com.example.mostrecommended

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

//event myAdapter includes the activities related to event calendar

class evemyAdapter(
    private val context: android.content.Context,
    private val dataList: List<eveDataClass>
) : RecyclerView.Adapter<eveMyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): eveMyViewHolder {
        val view2: View =
            LayoutInflater.from(parent.context).inflate(R.layout.everecycler_item, parent, false)
        return eveMyViewHolder(view2)
    }

    override fun onBindViewHolder(holder: eveMyViewHolder, position: Int) {
        Glide.with(context).load(dataList[position].dataImageE).into(holder.recImageE)
        holder.recTitleE.text = dataList[position].dataTitleE
        holder.recDescE.text = dataList[position].dataDescE
        holder.recPriorityE.text = dataList[position].dataPriorityE
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}

class eveMyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var recImageE: ImageView
    var recTitleE: TextView
    var recDescE: TextView
    var recPriorityE: TextView
    var recCardE: CardView

    init {
        recImageE = itemView.findViewById(R.id.recImageE)
        recTitleE = itemView.findViewById(R.id.recTitleE)
        recDescE = itemView.findViewById(R.id.recDescE)
        recPriorityE = itemView.findViewById(R.id.recPriorityE)
        recCardE = itemView.findViewById(R.id.recCardE)
    }
}