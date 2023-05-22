package com.example.material_design.view.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.material_design.R

class RecyclerActivityAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: List<Data>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == Data.TYPE_EARTH){
            EarthViewHolder(
                inflater.inflate(R.layout.activity_recycler_item_earth, parent,
                    false) as View
            )
        }
        else{
            MarsViewHolder(
                inflater.inflate(R.layout.activity_recycler_item_mars, parent,
                    false) as View
            )
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == Data.TYPE_EARTH) {
            holder as EarthViewHolder
            holder.bind(data[position])
        } else {
            holder as MarsViewHolder
            holder.bind(data[position])
        }
    }


    inner class EarthViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Data) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.descriptionTextView).text =
                    data.someDescription
                itemView.findViewById<ImageView>(R.id.wikiImageView).setOnClickListener {
                    onListItemClickListener.onItemClick(data) }
            }
        }
    }
    inner class MarsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Data) {
            itemView.findViewById<ImageView>(R.id.marsImageView).setOnClickListener {
                onListItemClickListener.onItemClick(data) }
        }
    }

}