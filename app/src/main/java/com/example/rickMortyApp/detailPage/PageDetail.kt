package com.example.rickMortyApp.detailPage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.rickMortyApp.R
import com.example.rickMortyApp.adaptor.Adaptor
import com.example.rickMortyApp.adaptor.StringAdaptor
import com.example.rickMortyApp.databinding.FragmentPageDetailBinding
import com.example.rickMortyApp.network.Character
import com.example.rickMortyApp.ulti.ScreenState
import com.example.rickMortyApp.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar


class PageDetail : Fragment() {
    private var _binding: FragmentPageDetailBinding? = null
    private val args: PageDetailArgs by navArgs()
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

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

    view.findViewById<ImageView>(R.id.image_arrow_home).setOnClickListener {
        requireActivity().onBackPressed()
        //            requireActivity().setResult(Activity.RESULT_OK)
        //            requireActivity().finish()
    }

    val img = binding.detailImage
    val name = binding.detailName
    val species = binding.detailSpecies
    val gender = binding.detailGender
    val created = binding.detailCreated
    val status = binding.detailStatus
    val url = binding.detailUrl
    val nameLocation = binding.detailName2
    val urlLocation = binding.detailUrl2



    name.text = args.name
    species.text = args.species
    gender.text = args.gender
    created.text = args.created
    status.text = args.status
    url.text = args.url
    Linkify.addLinks(url, Linkify.WEB_URLS)
    img.load(args.image) { transformations() }
    nameLocation.text = args.nameLocation

    urlLocation.text = args.urlLocation
    Linkify.addLinks(urlLocation, Linkify.WEB_URLS)

    //        episode.text = args.episode[0]

    val adaptorDetail = StringAdaptor(args.episode.toList())
    val episodeRecycler = binding.recycleViewListStringHomePage
    episodeRecycler.layoutManager =
        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    episodeRecycler.adapter = adaptorDetail
    adaptorDetail.setOnItemClickListener(object : StringAdaptor.OnItemClickListener {
        override fun onItemClick(position: Int) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(args.episode[position])
            startActivity(intent)
        }
    }
    )
}
}