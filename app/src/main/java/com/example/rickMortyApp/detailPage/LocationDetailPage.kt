package com.example.rickMortyApp.detailPage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickMortyApp.R
import com.example.rickMortyApp.adaptor.ResidentsAdaptor
import com.example.rickMortyApp.databinding.FragmentFirstBinding
import com.example.rickMortyApp.network.*
import com.example.rickMortyApp.viewmodel.MainViewModel


class LocationDetailPage : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val arg : LocationDetailPageArgs by navArgs()
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location_detail_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageView>(R.id.image_arrow_location).setOnClickListener{
            requireActivity().onBackPressed()
//            requireActivity().setResult(Activity.RESULT_OK)
//            requireActivity().finish()
        }

        val name = view.findViewById<TextView>(R.id.textLc_name)
        val created = view.findViewById<TextView>(R.id.textLc_created)
        val url = view.findViewById<TextView>(R.id.textLc_url)
        val dimension = view.findViewById<TextView>(R.id.textLc_dimension)
        val type = view.findViewById<TextView>(R.id.textLc_type)

        name.text =  arg.name
        created.text = arg.created
        url.text = arg.url
        dimension.text = arg.dimension
        type.text = arg.type

        // convert array string to list
//        val listResidents = arg.residents.toList().map {
//            Residents(
//                name = it, gender = it,
//                species = null, type = null ,
//                status = it, origin = null,
//                location = null , image = it ,
//                episode = null , url = it,
//                created = it
//            )
//        }
        val listResidents = arg.residents.toList()
        val adaptor = ResidentsAdaptor(listResidents)
        println(">>>>> $adaptor")
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycle_location_detail_residents)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adaptor
        adaptor.setOnItemClickListener(object : ResidentsAdaptor.OnItemClickListener {
            override fun onItemClick(position: Int) {
                findNavController().navigate(R.id.residentsDetail, bundleOf(
                    "listResidents" to listResidents[position]
                ))
//                val residentName =  listResidents[position].name
//                val residentImage = listResidents[position].image
//                val residentCreated = listResidents[position].created
//                val residentStatus = listResidents[position].status
//                val residentUrl = listResidents[position].url
//                val residentGender = listResidents[position].gender
//
//                findNavController().navigate(R.id.residentsDetail, bundleOf(
//                    "name" to residentName,
//                    "image" to residentImage,
//                    "created" to residentCreated,
//                    "status" to residentStatus,
//                    "url" to residentUrl,
//                    "gender" to residentGender
//                )
//                )
            }

        })
    }
}