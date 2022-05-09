package com.example.rickMortyApp.view.pages


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickMortyApp.R
import com.example.rickMortyApp.adaptor.Adaptor
import com.example.rickMortyApp.databinding.FragmentFirstBinding
import com.example.rickMortyApp.network.Character
import com.example.rickMortyApp.ulti.ScreenState
import com.example.rickMortyApp.viewmodel.CharacterViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FirstFragment : Fragment()  {
    private val viewModel: CharacterViewModel by lazy {
        ViewModelProvider(this)[CharacterViewModel::class.java]
    }
//    private val vm : CharacterViewModel by viewModels()
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.characterLiveData.observe(viewLifecycleOwner) { state ->
            processCharacterResponse(state)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun processCharacterResponse(state: ScreenState<List<Character>?>){
        val pd = binding.processBar
        when(state){
            is ScreenState.Loading ->{
                pd.visibility = View.VISIBLE
            }
            is ScreenState.Success -> {
                pd.visibility = View.GONE
                if (state.data != null){
                    val adaptor = Adaptor(state.data)
                    val recyclerView = binding.recycleViewItems
                    recyclerView.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                    recyclerView.adapter = adaptor
                    adaptor.setOnItemClickListener(object : Adaptor.OnItemClickListener{
                        override fun onItemClick(position: Int) {
                            val dataName = state.data[position].name
                            val dataImage = state.data[position].image
                            val dataSpecies = state.data[position].species
                            val dataGender = state.data[position].gender
                            val dataCreated = state.data[position].created
                            val dataStatus = state.data[position].status
                            val dataUrl = state.data[position].url
                            ///fetch data location
                            val dataLocation = state.data[position].location
                            val dataNameLocation = dataLocation.name
                            val dataUrlLocation = dataLocation.url
                            // episode list of string
                            val dataEpisode = state.data[position].episode
                            println("dataName $dataEpisode")

                            findNavController().navigate(
                                R.id.pageDetail, bundleOf(
                                "name" to dataName,
                                "image" to dataImage,
                                "species" to dataSpecies,
                                "gender" to dataGender,
                                "created" to dataCreated,
                                "status" to dataStatus,
                                "urlLocation" to dataUrlLocation,
                                "nameLocation" to dataNameLocation,
                                "url" to dataUrl,
                                "episode" to dataEpisode.toTypedArray()
                            )
                            )}
                    })
                }
            } is ScreenState.Error -> {
                pd.visibility = View.GONE
                val view = pd.rootView
                Snackbar.make(view,state.message.toString(),Snackbar.LENGTH_LONG).show()
            }
        }
    }
}



//
//viewLifecycleOwner.lifecycleScope.launch {
//    vm.fetchCharacterDI()?.let {
//        val adaptor = Adaptor(it.results)
//        val recyclerView = binding.recycleViewItems
//        recyclerView.layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
//        recyclerView.adapter = adaptor
//        adaptor.setOnItemClickListener(object : Adaptor.OnItemClickListener{
//            override fun onItemClick(position: Int) {
//                val dataName = it.results[position].name
//                val dataImage = it.results[position].image
//                val dataSpecies = it.results[position].species
//                val dataGender = it.results[position].gender
//                val dataCreated = it.results[position].created
//                val dataStatus = it.results[position].status
//                val dataUrl = it.results[position].url
//                ///fetch data location
//                val dataLocation = it.results[position].location
//                val dataNameLocation = dataLocation.name
//                val dataUrlLocation = dataLocation.url
//                // episode list of string
//                val dataEpisode = it.results[position].episode
//
//                findNavController().navigate(
//                    R.id.pageDetail, bundleOf(
//                        "name" to dataName,
//                        "image" to dataImage,
//                        "species" to dataSpecies,
//                        "gender" to dataGender,
//                        "created" to dataCreated,
//                        "status" to dataStatus,
//                        "urlLocation" to dataUrlLocation,
//                        "nameLocation" to dataNameLocation,
//                        "url" to dataUrl,
//                        "episode" to dataEpisode.toTypedArray()
//                    )
//                )}
//        })
//    }
//}
//
//


