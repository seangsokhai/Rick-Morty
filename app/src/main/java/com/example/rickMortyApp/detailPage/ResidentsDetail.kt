package com.example.rickMortyApp.detailPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickMortyApp.R
import com.example.rickMortyApp.adaptor.ResidentsAdaptor
import com.example.rickMortyApp.databinding.FragmentFirstBinding
import com.example.rickMortyApp.network.Residents
import com.example.rickMortyApp.viewmodel.MainViewModel

class ResidentsDetail : Fragment() {
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
        return inflater.inflate(R.layout.fragment_residents_detail, container, false)
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
        val listResidents = arg.residents.toList().map {
            Residents(
                name = it, gender = null,
                species = null, type = null ,
                status = null, origin = null,
                location = null , image = null ,
                episode = null , url = null,
                created = null
            )
        }

        val adaptor = ResidentsAdaptor(listResidents)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycle_location_detail_residents)

        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adaptor
        adaptor.setOnItemClickListener(object : ResidentsAdaptor.OnItemClickListener {
            override fun onItemClick(position: Int) {
                println(">> clicked")
            }
        }
        )

    }

}