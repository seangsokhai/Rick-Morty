package com.example.rickMortyApp.view.pages

import android.os.Bundle
import android.view.*
import android.widget.SearchView

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickMortyApp.R
import com.example.rickMortyApp.adaptor.EpisodeAdaptor
import com.example.rickMortyApp.databinding.FragmentEpisodePageBinding
import com.example.rickMortyApp.viewmodel.EpisodeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodePage : Fragment() , SearchView.OnQueryTextListener{
    private val vm: EpisodeViewModel by viewModels()
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
        setupSearchView()
        viewLifecycleOwner.lifecycleScope.launch {
            binding.processBarEpisodePage.visibility = View.VISIBLE
            vm.fetchEpisode()?.let{
                binding.processBarEpisodePage.visibility = View.GONE
                val adaptor = EpisodeAdaptor(it.results)
                val recyclerView = view.findViewById<RecyclerView>(R.id.recycle_view_episode_items)
                recyclerView?.layoutManager =
                    LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                recyclerView?.adapter = adaptor

                adaptor.setOnItemClickListener(object : EpisodeAdaptor.OnItemClickListener{
                    override fun onItemClick(position: Int) {
                        findNavController().navigate(
                            R.id.episodeDetailPage, bundleOf(
                                "name" to it.results[position].name,
                                "airDate" to it.results[position].air_date,
                                "created" to it.results[position].created,
                                "url" to it.results[position].url,
                                "episode" to it.results[position].episode,
                                "character" to it.results[position].characters.toTypedArray()
                        ))
                    }
                })
            }
        }
    }
    private fun setupSearchView(){
        val searchView: SearchView = binding.searchViewEpisodePage
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(p0: String): Boolean {
        viewLifecycleOwner.lifecycleScope.launch {
            binding.processBarEpisodePage.visibility = View.VISIBLE
            vm.fetchFilterEpisode("1", p0)?.let {
                binding.processBarEpisodePage.visibility = View.GONE
                val adaptor = EpisodeAdaptor(it.results)
                val recyclerView = view?.findViewById<RecyclerView>(R.id.recycle_view_episode_items)
                recyclerView?.layoutManager =
                    LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                recyclerView?.adapter = adaptor

                adaptor.setOnItemClickListener(object : EpisodeAdaptor.OnItemClickListener{
                    override fun onItemClick(position: Int) {
                        findNavController().navigate(
                            R.id.episodeDetailPage, bundleOf(
                                "name" to it.results[position].name,
                                "airDate" to it.results[position].air_date,
                                "created" to it.results[position].created,
                                "url" to it.results[position].url,
                                "episode" to it.results[position].episode,
                                "character" to it.results[position].characters.toTypedArray()
                            ))
                    }
                })
            }
        }
        return false
    }

    override fun onQueryTextChange(p0: String): Boolean {
        viewLifecycleOwner.lifecycleScope.launch {
            binding.processBarEpisodePage.visibility = View.VISIBLE
            vm.fetchFilterEpisode("1", p0)?.let {
                binding.processBarEpisodePage.visibility = View.GONE
                val adaptor = EpisodeAdaptor(it.results)
                val recyclerView = view?.findViewById<RecyclerView>(R.id.recycle_view_episode_items)
                recyclerView?.layoutManager =
                    LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                recyclerView?.adapter = adaptor

                adaptor.setOnItemClickListener(object : EpisodeAdaptor.OnItemClickListener{
                    override fun onItemClick(position: Int) {
                        findNavController().navigate(
                            R.id.episodeDetailPage, bundleOf(
                                "name" to it.results[position].name,
                                "airDate" to it.results[position].air_date,
                                "created" to it.results[position].created,
                                "url" to it.results[position].url,
                                "episode" to it.results[position].episode,
                                "character" to it.results[position].characters.toTypedArray()
                            ))
                    }
                })
            }
        }
        return false
    }

    //        viewModel.episodeLiveData.observe(viewLifecycleOwner) { state ->
//            processLocationResponse(state)
//        }


//    private fun processLocationResponse(state: ScreenState<List<EpisodeData>?>){
//        val pb = binding.processBarEpisodePage
//        when(state){
//            is ScreenState.Loading ->{
//                pb.visibility = View.VISIBLE
//            }
//            is ScreenState.Success -> {
//                pb.visibility = View.GONE
//                println(">> data ${state.data}")
//
//                if (state.data != null){
//                    val adaptor = EpisodeAdaptor(state.data)
//                    val recyclerView = view?.findViewById<RecyclerView>(R.id.recycle_view_episode_items)
//                    recyclerView?.layoutManager =
//                        LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
//                    recyclerView?.adapter = adaptor
//
//                    adaptor.setOnItemClickListener(object : EpisodeAdaptor.OnItemClickListener{
//                        override fun onItemClick(position: Int) {
//                            val episodeName = state.data[position].name
//                            val episodeCreated = state.data[position].created
//                            val episodeUrl = state.data[position].url
//                            val episodeAirDate = state.data[position].air_date
//                            val episodeEpisode = state.data[position].episode
//                            // episode list of string
//                            val episodeCharacter = state.data[position].characters
//
//                            findNavController().navigate(
//                                R.id.episodeDetailPage, bundleOf(
//                                "name" to episodeName,
//                                "airDate" to episodeAirDate,
//                                "created" to episodeCreated,
//                                "url" to episodeUrl,
//                                "episode" to episodeEpisode,
//                                "character" to episodeCharacter.toTypedArray()
//                            ))
//                            }
//                    })
//                }
//            } is ScreenState.Error -> {
//                pb.visibility = View.GONE
//                val view = pb.rootView
//                Snackbar.make(view!!,state.message.toString(),Snackbar.LENGTH_LONG).show()
//        }
//        }
//    }



}


