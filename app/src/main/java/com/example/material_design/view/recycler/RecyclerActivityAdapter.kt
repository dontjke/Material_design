package com.example.material_design.view.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.material_design.R
import com.example.material_design.view.recycler.Data.Companion.TYPE_EARTH
import com.example.material_design.view.recycler.Data.Companion.TYPE_MARS

class RecyclerActivityAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: MutableList<Data>
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_EARTH -> EarthViewHolder(
                inflater.inflate(R.layout.activity_recycler_item_earth, parent, false)
                        as View
            )
            TYPE_MARS ->
                MarsViewHolder(
                    inflater.inflate(
                        R.layout.activity_recycler_item_mars, parent,
                        false
                    ) as View
                )
            else -> HeaderViewHolder(
                inflater.inflate(
                    R.layout.activity_recycler_item_header, parent,
                    false
                ) as View
            )
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(data[position])
    }


    inner class EarthViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Data) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.descriptionTextView).text =
                    data.someDescription
                itemView.findViewById<ImageView>(R.id.wikiImageView).setOnClickListener {
                    onListItemClickListener.onItemClick(data)
                }
            }
        }
    }

    fun appendItem() {
        data.add(generateItem())
        notifyDataSetChanged()
    }
    private fun generateItem() = Data(TYPE_MARS, "Mars", "")
    inner class MarsViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Data) {
            itemView.findViewById<ImageView>(R.id.marsImageView).setOnClickListener {
                onListItemClickListener.onItemClick(data)
            }

            itemView.findViewById<ImageView>(R.id.addItemImageView).setOnClickListener {
                addItem() }
            itemView.findViewById<ImageView>(R.id.removeItemImageView).setOnClickListener{
                removeItem() }
        }

        private fun addItem() {
            data.add(layoutPosition, generateItem())
            notifyDataSetChanged()
        }
        private fun removeItem() {
            data.removeAt(layoutPosition)
            notifyDataSetChanged()
        }
    }

    inner class HeaderViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Data) {
            itemView.setOnClickListener { onListItemClickListener.onItemClick(data) }
        }
    }


}