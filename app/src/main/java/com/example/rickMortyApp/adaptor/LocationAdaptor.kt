package com.example.rickMortyApp.adaptor


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickMortyApp.R
import com.example.rickMortyApp.network.LocationData
import com.example.rickMortyApp.network.Character

class LocationAdaptor(private val locationList: List<LocationData>): RecyclerView.Adapter<LocationAdaptor.MainViewHolder>() {
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

        fun bindData(locationData: LocationData){
            //adapt view with model json
            itemView.findViewById<TextView>(R.id.tv_name_location).text = locationData.name

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_location_page , parent ,false)
        return MainViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        return holder.bindData(locationList[position])
    }

    override fun getItemCount(): Int {
        return locationList.size
    }

}

class ResidentAdaptor(private val residentList: List<Character>): RecyclerView.Adapter<ResidentAdaptor.MainViewHolder>() {
    private  lateinit var mListener: ResidentAdaptor.OnItemClickListener
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

        fun bindData(character: Character){

            itemView.findViewById<TextView>(R.id.tv_resident_name).text = character.name
            itemView.findViewById<TextView>(R.id.tv_resident_status).text = character.status
            itemView.findViewById<TextView>(R.id.tv_resident_species).text = character.species
            itemView.findViewById<TextView>(R.id.tv_resident_gender).text = character.gender
            itemView.findViewById<TextView>(R.id.tv_resident_created).text = character.created
            itemView.findViewById<ImageView>(R.id.tv_resident_image).load(character.image){
                transformations()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_location_residents , parent ,false)
        return MainViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        return holder.bindData(residentList[position])
    }

    override fun getItemCount(): Int {
        return residentList.size
    }

}