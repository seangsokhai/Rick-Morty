package com.example.rickMortyApp.adaptor


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickMortyApp.R
import com.example.rickMortyApp.network.LocationData
import com.example.rickMortyApp.network.Residents

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
//            val type = itemView.findViewById<TextView>(R.id.tv_type_location)
//            val dimension = itemView.findViewById<TextView>(R.id.tv_dimension_location)
            val name = itemView.findViewById<TextView>(R.id.tv_name_location)

            //adapt view with model json
            name.text = locationData.name
//            type.text = locationData.type
//            dimension.text = locationData.dimension

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


class ResidentsAdaptor(private val residentList: List<Residents>): RecyclerView.Adapter<ResidentsAdaptor.MainViewHolder>() {
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
            }
        }
        fun bindData(residents: Residents){
            val name = itemView.findViewById<TextView>(R.id.detail_location_resident)
            //adapt view with model json
            name.text = residents.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_location_residents , parent ,false)
        return MainViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        return holder.bindData(residentList[position])
    }

    override fun getItemCount(): Int {
        return residentList.size
    }

}

