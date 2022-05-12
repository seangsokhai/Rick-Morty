package com.example.rickMortyApp.view.pages


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickMortyApp.R
import com.example.rickMortyApp.adaptor.Adaptor
import com.example.rickMortyApp.adaptor.EpisodeListCharacterAdaptor
import com.example.rickMortyApp.databinding.FragmentFirstBinding
import com.example.rickMortyApp.network.ApiClient
import com.example.rickMortyApp.network.Character
import com.example.rickMortyApp.network.CharacterResponse
import com.example.rickMortyApp.ulti.ScreenState
import com.example.rickMortyApp.viewmodel.CharacterViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class FirstFragment : Fragment() , SearchView.OnQueryTextListener {
//    private val viewModel: CharacterViewModel by lazy {
//        ViewModelProvider(this)[CharacterViewModel::class.java]
//    }
    private val vm : CharacterViewModel by viewModels()
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
        binding.floatingActionButton.setOnClickListener {
            binding.searchViewCharacterPage.visibility = View.VISIBLE
            setUpSearchCharacter()
        }
//        viewModel.characterLiveData.observe(viewLifecycleOwner) { state ->
//            processCharacterResponse(state)
//        }
        viewLifecycleOwner.lifecycleScope.launch {
            binding.searchViewCharacterPage.visibility = View.VISIBLE
            vm.fetchCharacterS("1")?.let {
                binding.searchViewCharacterPage.visibility = View.GONE
                val adaptor = Adaptor(it.results)
                val recyclerView = binding.recycleViewItems
                recyclerView.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                recyclerView.adapter = adaptor
                adaptor.setOnItemClickListener(object : Adaptor.OnItemClickListener{
                    override fun onItemClick(position: Int) {
                        ///fetch data location
                        val dataLocation = it.results[position].location
                        findNavController().navigate(
                            R.id.pageDetail, bundleOf(
                                "name" to it.results[position].name,
                                "image" to it.results[position].image,
                                "species" to it.results[position].species,
                                "gender" to it.results[position].gender,
                                "created" to it.results[position].created,
                                "status" to it.results[position].status,
                                "urlLocation" to dataLocation.url,
                                "nameLocation" to dataLocation.name,
                                "url" to it.results[position].url,
                                "episode" to it.results[position].episode.toTypedArray()
                            )
                        )}
                })
            }
        }
    }
    private fun setUpSearchCharacter(){
        val searchView : SearchView = binding.searchViewCharacterPage
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
    }
    override fun onQueryTextSubmit(p0: String): Boolean {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO){
           val client = ApiClient.apiService.fetchFilterCharacter("1","$p0")
           client.enqueue(object : Callback<CharacterResponse> {
            override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                throw(Error("Character: ${t.message.toString()}"))
            }
            override fun onResponse(
                call: Call<CharacterResponse>,
                response: Response<CharacterResponse>
            ) {
                if (response.isSuccessful){
                    val result = response.body()?.results
                    val adaptor = result?.let { Adaptor(it) }
                    val recyclerView = binding.recycleViewItems
                    recyclerView.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                    recyclerView.adapter = adaptor
                    adaptor?.setOnItemClickListener(object : Adaptor.OnItemClickListener{
                        override fun onItemClick(position: Int) {
                            val dataName = result[position].name
                            val dataImage = result[position].image
                            val dataSpecies = result[position].species
                            val dataGender = result[position].gender
                            val dataCreated = result[position].created
                            val dataStatus = result[position].status
                            val dataUrl = result[position].url
                            ///fetch data location
                            val dataLocation = result[position].location
                            val dataNameLocation = dataLocation.name
                            val dataUrlLocation = dataLocation.url
                            // episode list of string
                            val dataEpisode = result[position].episode

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
                }
           })
        }
        return false
    }
    override fun onQueryTextChange(p0: String): Boolean {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO){
            val client = ApiClient.apiService.fetchFilterCharacter("1","$p0")
            client.enqueue(object : Callback<CharacterResponse> {
                override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                    throw(Error("Character: ${t.message.toString()}"))
                }
                override fun onResponse(
                    call: Call<CharacterResponse>,
                    response: Response<CharacterResponse>
                ) {
                    if (response.isSuccessful){
                        val result = response.body()?.results
                        val adaptor = result?.let { Adaptor(it) }
                        val recyclerView = binding.recycleViewItems
                        recyclerView.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                        recyclerView.adapter = adaptor
                        adaptor?.setOnItemClickListener(object : Adaptor.OnItemClickListener{
                            override fun onItemClick(position: Int) {
                                val dataName = result[position].name
                                val dataImage = result[position].image
                                val dataSpecies = result[position].species
                                val dataGender = result[position].gender
                                val dataCreated = result[position].created
                                val dataStatus = result[position].status
                                val dataUrl = result[position].url
                                ///fetch data location
                                val dataLocation = result[position].location
                                val dataNameLocation = dataLocation.name
                                val dataUrlLocation = dataLocation.url
                                // episode list of string
                                val dataEpisode = result[position].episode

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
                }
            })
        }
        return false
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


//viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO){
//    vm.fetchFilterCharacter("1","$p0").let {
//        val adaptor = it?.let { it1 -> Adaptor(it1.results) }
//        val recyclerView = binding.recycleViewItems
//        recyclerView.layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
//        recyclerView.adapter = adaptor
//        adaptor?.setOnItemClickListener(object : Adaptor.OnItemClickListener{
//            override fun onItemClick(position: Int) {
//                ///fetch data location
//                val dataLocation = it.results[position].location
//                findNavController().navigate(
//                    R.id.pageDetail, bundleOf(
//                        "name" to it.results[position].name,
//                        "image" to it.results[position].image,
//                        "species" to it.results[position].species,
//                        "gender" to it.results[position].gender,
//                        "created" to it.results[position].created,
//                        "status" to it.results[position].status,
//                        "urlLocation" to dataLocation.url,
//                        "nameLocation" to dataLocation.name,
//                        "url" to it.results[position].url,
//                        "episode" to it.results[position].episode.toTypedArray()
//                    )
//                )}
//        })
//    }
//
//}

//
//private fun processCharacterResponse(state: ScreenState<List<Character>?>){
//    val pd = binding.processBar
//    when(state){
//        is ScreenState.Loading ->{
//            pd.visibility = View.VISIBLE
//        }
//        is ScreenState.Success -> {
//            pd.visibility = View.GONE
//            if (state.data != null){
//                val adaptor = Adaptor(state.data)
//                val recyclerView = binding.recycleViewItems
//                recyclerView.layoutManager =
//                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
//                recyclerView.adapter = adaptor
//                adaptor.setOnItemClickListener(object : Adaptor.OnItemClickListener{
//                    override fun onItemClick(position: Int) {
//                        val dataName = state.data[position].name
//                        val dataImage = state.data[position].image
//                        val dataSpecies = state.data[position].species
//                        val dataGender = state.data[position].gender
//                        val dataCreated = state.data[position].created
//                        val dataStatus = state.data[position].status
//                        val dataUrl = state.data[position].url
//                        ///fetch data location
//                        val dataLocation = state.data[position].location
//                        val dataNameLocation = dataLocation.name
//                        val dataUrlLocation = dataLocation.url
//                        // episode list of string
//                        val dataEpisode = state.data[position].episode
//                        println("dataName $dataEpisode")
//
//                        findNavController().navigate(
//                            R.id.pageDetail, bundleOf(
//                                "name" to dataName,
//                                "image" to dataImage,
//                                "species" to dataSpecies,
//                                "gender" to dataGender,
//                                "created" to dataCreated,
//                                "status" to dataStatus,
//                                "urlLocation" to dataUrlLocation,
//                                "nameLocation" to dataNameLocation,
//                                "url" to dataUrl,
//                                "episode" to dataEpisode.toTypedArray()
//                            )
//                        )}
//                })
//            }
//        } is ScreenState.Error -> {
//        pd.visibility = View.GONE
//        val view = pd.rootView
//        Snackbar.make(view,state.message.toString(),Snackbar.LENGTH_LONG).show()
//    }
//    }
//}





