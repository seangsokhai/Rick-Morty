package com.example.rickMortyApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickMortyApp.adaptor.Adaptor
import com.example.rickMortyApp.adaptor.EpisodeAdaptor
import com.example.rickMortyApp.databinding.FragmentEpisodePageBinding
import com.example.rickMortyApp.databinding.FragmentFirstBinding
import com.example.rickMortyApp.network.EpisodeData
import com.example.rickMortyApp.ulti.ScreenState
import com.example.rickMortyApp.viewmodel.MainViewModel


class EpisodePage : Fragment() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private var _binding: FragmentEpisodePageBinding? = null
    private val binding get() = _binding!!
    lateinit var searchView: SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_episode_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.episodeLiveData.observe(viewLifecycleOwner) { state ->
            processLocationResponse(state)
        }
    }
    private fun processLocationResponse(state: ScreenState<List<EpisodeData>?>){

        when(state){
            is ScreenState.Loading ->{
                println("loadingfffffffffEEEEEEEEE")
            }
            is ScreenState.Success -> {

                println(">> data ${state.data}")

                if (state.data != null){
                    val adaptor = EpisodeAdaptor(state.data)
                    val recyclerView = view?.findViewById<RecyclerView>(R.id.recycle_view_episode_items)
                    recyclerView?.layoutManager =
                        LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                    recyclerView?.adapter = adaptor

                    adaptor.setOnItemClickListener(object : EpisodeAdaptor.OnItemClickListener{
                        override fun onItemClick(position: Int) {
                            val episodeName = state.data[position].name
                            val episodeCreated = state.data[position].created
                            val episodeUrl = state.data[position].url
                            val episodeAirDate = state.data[position].air_date
                            val episodeEpisode = state.data[position].episode
                            // episode list of string
                            val episodeCharacter = state.data[position].characters
//                            println("dataName $dataEpisode")

                            findNavController().navigate(R.id.episodeDetailPage, bundleOf(
                                "name" to episodeName,
                                "airDate" to episodeAirDate,
                                "created" to episodeCreated,
                                "url" to episodeUrl,
                                "episode" to episodeEpisode,
                                "character" to episodeCharacter.toTypedArray()
                            ))
                            }
                    })
//                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                        override fun onQueryTextSubmit(query: String): Boolean {
//                            val list = state.data.toString()
//                            searchView.clearFocus()
//                            if (list.contains(query)) {
//                                adaptor.filter.filter(query)
//                            } else {
//                                Toast.makeText(context, "No Match found", Toast.LENGTH_LONG).show()
//                            }
//                            return false
//                        }
//                        override fun onQueryTextChange(newText: String): Boolean {
//                            adaptor.filter.filter(newText)
//                            return false
//                        }
//                    })
                }
            } is ScreenState.Error -> {
            println("error")

        }
        }
    }
}