package com.example.rickMortyApp

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.rickMortyApp.databinding.FragmentPageDetailBinding


class PageDetail : Fragment() {
    private var _binding: FragmentPageDetailBinding? = null
    private val args: PageDetailArgs by navArgs()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPageDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

//    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn = binding.imageArrow
        val img = binding.detailImage
        val name = binding.detailName
        val species = binding.detailSpecies
        val gender = binding.detailGender
        val created = binding.detailCreated
        val status = binding.detailStatus
        val url = binding.detailUrl
        val nameLocation = binding.detailName2
        val urlLocation = binding.detailUrl2
        val episode = binding.detailEpisode


        name.text =  args.name
        species.text =  args.species
        gender.text =  args.gender
        created.text =  args.created
        status.text =  args.status
        url.text =  args.url
        Linkify.addLinks(url,Linkify.WEB_URLS)
        img.load(args.image){transformations()}
        nameLocation.text = args.nameLocation
        urlLocation.text = args.urlLocation
        Linkify.addLinks(urlLocation, Linkify.WEB_URLS)
        episode.text = args.episode[0]
        Linkify.addLinks(episode,Linkify.WEB_URLS)

    }


}