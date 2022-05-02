package com.example.rickMortyApp.view.detailPage.episode

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

class EpisodeListOfCharacter : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val args: EpisodeListOfCharacterArgs by navArgs()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_episode_list_of_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageView>(R.id.image_arrow_EpiCharacter).setOnClickListener{
            requireActivity().onBackPressed()
        }

        val name = view.findViewById<TextView>(R.id.epiCharacter_name)
        val status = view.findViewById<TextView>(R.id.epiCharacter_status)
        val created = view.findViewById<TextView>(R.id.epiCharacter_created)
        val url = view.findViewById<TextView>(R.id.epiCharacter_url)
        val gender = view.findViewById<TextView>(R.id.epiCharacter_gender)
        val species = view.findViewById<TextView>(R.id.epiCharacter_species)
        val image = view.findViewById<ImageView>(R.id.epiCharacter_image)

        name.text =  args.name
        status.text = args.status
        created.text = args.created
        url.text = args.url
        gender.text = args.gender
        species.text = args.species
        image.load(args.image){}
    }


}