package com.example.neetprep

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class DynamicAdapter(val context:Context,val dynamicList:List<DynamicModel>) : RecyclerView.Adapter<DynamicAdapter.DynamicAdapterViewHolder>() {


    var onItemClick: ((DynamicModel) -> Unit)? = null
    inner class DynamicAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        var title:TextView? = null
        var image:ImageView? = null
        init {

            title = itemView.findViewById(R.id.tv_title)
            image = itemView.findViewById(R.id.iv_category)
            itemView.setOnClickListener {
                onItemClick?.invoke(dynamicList[adapterPosition])
            }
        }

        override fun onClick(p0: View?) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DynamicAdapterViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val view = inflator.inflate(R.layout.item_dynamic,parent,false)
        return DynamicAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
       return dynamicList.size
    }

    override fun onBindViewHolder(holder: DynamicAdapterViewHolder, position: Int) {
        if(dynamicList.isNotEmpty()) {
            holder.title?.text = dynamicList.get(position).title
            Picasso.get().load(dynamicList[position].image).into(holder.image)
        }
    }
}