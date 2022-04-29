package com.example.rickMortyApp

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickMortyApp.adaptor.EpisodeAdaptor
import com.example.rickMortyApp.databinding.FragmentEpisodePageBinding
import com.example.rickMortyApp.databinding.FragmentLocationPageBinding
import com.example.rickMortyApp.network.EpisodeData
import com.example.rickMortyApp.ulti.ScreenState
import com.example.rickMortyApp.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar


class EpisodePage : Fragment(), SearchView.OnQueryTextListener {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private var _binding: FragmentEpisodePageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEpisodePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.episodeLiveData.observe(viewLifecycleOwner) { state ->
            processLocationResponse(state)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        val search = menu.findItem(R.menu.menu_main)
        val searchView = search.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)



    }

    private fun processLocationResponse(state: ScreenState<List<EpisodeData>?>){
        val pb = binding.processBarEpisodePage
        when(state){
            is ScreenState.Loading ->{
                pb.visibility = View.VISIBLE
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
                }
            } is ScreenState.Error -> {
                pb.visibility = View.GONE
                val view = pb.rootView
                Snackbar.make(view!!,state.message.toString(),Snackbar.LENGTH_LONG).show()
        }
        }
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        if (p0 != null) {
            searchDatabase(p0)
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        if (p0 != null) {
            searchDatabase(p0)
        }
        return true
    }

    private fun searchDatabase(p0: String?){
        val searchQuery = "%$p0"
        /**
         * search value
         * send to api
         * api return data
         * data shows in list
         */

//        viewModel.episodeLiveData.searchDatabase(searchQuery).observe(viewLifecycleOwner) { state ->
//            state.let {
//                EpisodeAdaptor.setData(it)
//            }
//        }
    }
}