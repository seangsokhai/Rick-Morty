package com.example.rickMortyApp.view.detailPage.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.rickMortyApp.R
import com.example.rickMortyApp.databinding.FragmentFirstBinding

class ResidentsDetail : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val args : ResidentsDetailArgs by navArgs()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_residents_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageView>(R.id.image_arrow_Resident).setOnClickListener{
            requireActivity().onBackPressed()
        }

        val name = view.findViewById<TextView>(R.id.resident_name)
        val status = view.findViewById<TextView>(R.id.resident_status)
        val created = view.findViewById<TextView>(R.id.resident_created)
        val url = view.findViewById<TextView>(R.id.resident_url)
        val gender = view.findViewById<TextView>(R.id.resident_gender)
        val species = view.findViewById<TextView>(R.id.resident_species)
        val image = view.findViewById<ImageView>(R.id.resident_image)

        name.text =  args.name
        status.text = args.status
        created.text = args.created
        url.text = args.url
        gender.text = args.gender
        species.text = args.species
        image.load(args.image){}
    }

}


