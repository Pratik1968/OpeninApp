package com.pratikshekhar.openinapp.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pratikshekhar.openinapp.R
import com.pratikshekhar.openinapp.adapter.InfoListAdapter.ViewHolder
import com.pratikshekhar.openinapp.uimodel.LinkListItemData

class linkListAdapter(private val data: List<LinkListItemData>, private val context: Context): RecyclerView.Adapter<linkListAdapter.ListViewHolder>() {


    class ListViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val image: ImageView
        val titleTextView:TextView
        val timeTextView:TextView
        val noOfClicksTextView:TextView

        init{
            image = itemView.findViewById(R.id.icon)
            titleTextView = itemView.findViewById(R.id.title)
            timeTextView = itemView.findViewById(R.id.time)
            noOfClicksTextView = itemView.findViewById(R.id.noOfClicks)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.item_link_list,parent,false)

        return ListViewHolder(view)
    }

    override fun onBindViewHolder(vh: ListViewHolder, position: Int) {
        vh.titleTextView.text = data[position].title
        vh.noOfClicksTextView.text = data[position].clicks
        vh.timeTextView.text = data[position].time
        Glide.with(context).load(data[position].imageUrl).placeholder(R.drawable.ic_launcher_background).into(vh.image)


    }
    override fun getItemCount(): Int  = data.size



}