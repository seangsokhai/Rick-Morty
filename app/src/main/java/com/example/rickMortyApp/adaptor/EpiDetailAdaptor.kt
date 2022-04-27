package com.example.rickMortyApp.adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickMortyApp.R
import com.example.rickMortyApp.network.EpisodeData

class EpiDetailAdaptor(private val episodeList: List<EpisodeData>): RecyclerView.Adapter<EpiDetailAdaptor.MainViewHolder>() {
    private  lateinit var mListener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }

    inner class MainViewHolder(itemView: View, private val listener: OnItemClickListener): RecyclerView.ViewHolder(itemView)  {
        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            } }

        fun bindData(episodeData: EpisodeData){
            val txt = itemView.findViewById<TextView>(R.id.episode_detail_txt)
            txt.text = episodeData.episode
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_episode_page, parent ,false)
        return MainViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        return holder.bindData(episodeList[position])
    }

    override fun getItemCount(): Int {
        return episodeList.size
    }

}

