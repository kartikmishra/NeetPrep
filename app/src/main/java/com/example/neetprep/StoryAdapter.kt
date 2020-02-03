package com.example.neetprep

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class StoryAdapter(val context: Context, val storyList:List<StoryModel>) : RecyclerView.Adapter<StoryAdapter.StoryAdapterViewHolder>() {


    class StoryAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView? = null
        var allIndiaRank: TextView? = null
        var neetScore: TextView? = null
        var image: ImageView? = null
        init {

            name = itemView.findViewById(R.id.tv_name)
            allIndiaRank = itemView.findViewById(R.id.tv_all_india_rank)
            neetScore = itemView.findViewById(R.id.tv_neet_score)
            image = itemView.findViewById(R.id.iv_profile)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryAdapterViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val view = inflator.inflate(R.layout.item_success_stories,parent,false)
        return StoryAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return storyList.size
    }

    override fun onBindViewHolder(holder: StoryAdapterViewHolder, position: Int) {
        if(storyList.isNotEmpty()) {

            holder.neetScore?.text = storyList[position].neetScore
            holder.allIndiaRank?.text = storyList[position].allIndiaRank
            holder.name?.text = storyList[position].name
            Picasso.get().load(storyList[position].image).fit().centerCrop().into(holder.image)
        }
    }
}