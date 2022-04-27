package com.example.rickMortyApp.adaptor


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickMortyApp.R
import com.example.rickMortyApp.network.EpisodeData

class EpisodeAdaptor(private val episodeList: List<EpisodeData>): RecyclerView.Adapter<EpisodeAdaptor.MainViewHolder>() {
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
            val title = itemView.findViewById<TextView>(R.id.tv_episode_title)
//            val dimension = itemView.findViewById<TextView>(R.id.tv_dimension_location)
            val name = itemView.findViewById<TextView>(R.id.tv_episode_text)
            //adapt view with model json
            name.text = episodeData.episode
            title.text = episodeData.name



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

