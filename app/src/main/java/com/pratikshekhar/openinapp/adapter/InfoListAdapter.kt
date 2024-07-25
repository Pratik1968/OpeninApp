package com.pratikshekhar.openinapp.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pratikshekhar.openinapp.R
import com.pratikshekhar.openinapp.uimodel.InfoListData

class InfoListAdapter(private val dataSet: List<InfoListData>): RecyclerView.Adapter<InfoListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
var image:ImageView
var label:TextView
var description:TextView
init{
    image =itemView.findViewById(R.id.image)
    label = itemView.findViewById(R.id.label)
    description = itemView.findViewById(R.id.description)

}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
val  view = LayoutInflater.from(parent.context).inflate(R.layout.item_info_card,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int =dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
holder.image.setImageResource(dataSet[position].drawableRes)
        holder.label.text =dataSet[position].label
        holder.description.text = dataSet[position].description


    }
}
