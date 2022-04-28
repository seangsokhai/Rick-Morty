package com.example.rickMortyApp.adaptor


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickMortyApp.R
import com.example.rickMortyApp.network.Character
import com.example.rickMortyApp.network.Location
import kotlinx.coroutines.joinAll

class Adaptor(private val characterList: List<Character>): RecyclerView.Adapter<Adaptor.MainViewHolder>() {
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
        fun bindData(character: Character){
            val name = itemView.findViewById<TextView>(R.id.tv_item_name)
            val image = itemView.findViewById<ImageView>(R.id.tv_item_image)
            val status = itemView.findViewById<TextView>(R.id.tv_item_status)
            val species = itemView.findViewById<TextView>(R.id.tv_item_species)
            val gender = itemView.findViewById<TextView>(R.id.tv_item_gender)
            val created = itemView.findViewById<TextView>(R.id.tv_item_created)
//            val list = itemView.findViewById<TextView>(R.id.detail_episode)

            //adapt view with model json
            name.text = character.name
            status.text = character.status
            species.text = character.species
            gender.text = character.gender
            created.text = character.created
            image.load(character.image){
                transformations()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_custom_row , parent ,false)
        return MainViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        return holder.bindData(characterList[position])
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

}

class StringAdaptor(private val characterList: List<String>): RecyclerView.Adapter<StringAdaptor.MainViewHolder>() {
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
        fun bindData(value: String){
            val name = itemView.findViewById<TextView>(R.id.detail_episode)

            //adapt view with model json
            name.text = value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_string_home_page_detail , parent ,false)
        return MainViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        return holder.bindData(characterList[position])
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

}