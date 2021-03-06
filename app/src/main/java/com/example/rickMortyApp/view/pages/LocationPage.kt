package com.example.rickMortyApp.view.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rickMortyApp.R
import com.example.rickMortyApp.adaptor.LocationAdaptor
import com.example.rickMortyApp.adaptor.StringAdapter
import com.example.rickMortyApp.databinding.FragmentLocationPageBinding
import com.example.rickMortyApp.ulti.Constants
import com.example.rickMortyApp.viewmodel.LocationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LocationPage : Fragment() {
//    private val viewModel: LocationViewModel by lazy {
//        ViewModelProvider(this)[LocationViewModel::class.java]
//    }
    private val vm: LocationViewModel by viewModels()
    private var _binding: FragmentLocationPageBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationPageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonType.setOnClickListener {
            binding.chipTypeRecyclerView.visibility = View.VISIBLE
            binding.chipDimensionRecyclerView.visibility = View.GONE
        }
        binding.buttonDimension.setOnClickListener {
          binding.chipTypeRecyclerView.visibility = View.GONE
          binding.chipDimensionRecyclerView.visibility = View.VISIBLE
        }
        //Filter Location
        binding.chipTypeRecyclerView.adapter = StringAdapter(Constants.types, action = { value ->
            filterLocation(value , "")
        })
        binding.chipDimensionRecyclerView.adapter = StringAdapter(Constants.dimension, action = { value ->
            filterLocation("", value)
        })

        viewLifecycleOwner.lifecycleScope.launch{
            binding.processBarLocationPage.visibility = View.VISIBLE
            vm.fetchLocation()?.let {
                binding.processBarLocationPage.visibility = View.GONE
                val adaptor = LocationAdaptor(it.results)
                val recyclerView = view.findViewById<RecyclerView>(R.id.recycle_location_items)
                recyclerView?.layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                recyclerView?.adapter = adaptor
                adaptor.setOnItemClickListener(object : LocationAdaptor.OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        findNavController().navigate(
                            R.id.locationDetailPage, bundleOf(
                                "name" to it.results[position].name,
                                "created" to it.results[position].created,
                                "url" to it.results[position].url,
                                "type" to it.results[position].type,
                                "dimension" to it.results[position].dimension,
                                "residents" to it.results[position].residents.toTypedArray()
                            )
                        )
                    }
                })
            }
        }

    }
    private fun filterLocation(type: String, dimension: String){
        binding.processBarLocationPage.visibility = View.VISIBLE
        viewLifecycleOwner.lifecycleScope.launch {
            vm.fetchFilterLocation("1", type, dimension).let {
                binding.processBarLocationPage.visibility = View.GONE
                val adaptor = it?.let { it1 -> LocationAdaptor(it1.results) }
                val recyclerView = view?.findViewById<RecyclerView>(R.id.recycle_location_items)
                recyclerView?.layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                recyclerView?.adapter = adaptor

                adaptor?.setOnItemClickListener(object : LocationAdaptor.OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        findNavController().navigate(
                            R.id.locationDetailPage, bundleOf(
                                "name" to it.results[position].name,
                                "created" to it.results[position].created,
                                "url" to it.results[position].url,
                                "type" to it.results[position].type,
                                "dimension" to it.results[position].dimension,
                                "residents" to it.results[position].residents.toTypedArray()
                            )
                        )
                    }
                })

            }
        }
    }
}





//        viewModel.locationLiveData.observe(viewLifecycleOwner) { state ->
//            processLocationResponse(state)
//        }


//    private fun filterLocation(type: String , dimension : String) {
//        val client = ApiClient.apiService.fetchFilterLocation("1", "$type" , "$dimension")
//
//        client.enqueue(object : retrofit2.Callback<LocationResponse> {
//            override fun onFailure(call: Call<LocationResponse>, t: Throwable) {
//                throw(Error("filterLocation: ${t.message.toString()}"))
//            }
//            override fun onResponse(
//                call: Call<LocationResponse>,
//                response: Response<LocationResponse>
//            ) {
//                if (response.isSuccessful) {
//                    val result = response.body()?.results
//                    val adaptor = result?.let { LocationAdaptor(it) }
//                    println(">>>>>>>>$adaptor")
//                    val recyclerView = view?.findViewById<RecyclerView>(R.id.recycle_location_items)
//                    recyclerView?.layoutManager =
//                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//                    recyclerView?.adapter = adaptor
//
//                    adaptor?.setOnItemClickListener(object : LocationAdaptor.OnItemClickListener {
//                        override fun onItemClick(position: Int) {
//                            val locationName = result[position].name
//                            val locationCreated = result[position].created
//                            val locationUrl = result[position].url
//                            val locationType = result[position].type
//                            val locationDimension = result[position].dimension
//
//                            val locationResidents = result[position].residents
//
//                            findNavController().navigate(
//                                R.id.locationDetailPage, bundleOf(
//                                    "name" to locationName,
//                                    "created" to locationCreated,
//                                    "url" to locationUrl,
//                                    "type" to locationType,
//                                    "dimension" to locationDimension,
//                                    "residents" to locationResidents.toTypedArray()
//                                )
//                            )
//                        }
//                    })
//                }
//            }
//        })
//    }



//    private fun processLocationResponse(state: ScreenState<List<LocationData>?>) {
//        val pd = binding.processBarLocationPage
//        when (state) {
//            is ScreenState.Loading -> {
//                pd.visibility = View.VISIBLE
//            }
//            is ScreenState.Success -> {
//                pd.visibility = View.GONE
//                if (state.data != null) {
//                    val adaptor = LocationAdaptor(state.data)
//                    println(">>>>>>>>$adaptor")
//                    val recyclerView = view?.findViewById<RecyclerView>(R.id.recycle_location_items)
//                    recyclerView?.layoutManager =
//                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//                    recyclerView?.adapter = adaptor
//
//                    adaptor.setOnItemClickListener(object : LocationAdaptor.OnItemClickListener {
//                        override fun onItemClick(position: Int) {
//                            println(position)
//
//                            val locationName = state.data[position].name
//                            val locationCreated = state.data[position].created
//                            val locationUrl = state.data[position].url
//                            val locationType = state.data[position].type
//                            val locationDimension = state.data[position].dimension
//
//
//                            val locationResidents = state.data[position].residents
//
//                            findNavController().navigate(
//                                R.id.locationDetailPage, bundleOf(
//                                    "name" to locationName,
//                                    "created" to locationCreated,
//                                    "url" to locationUrl,
//                                    "type" to locationType,
//                                    "dimension" to locationDimension,
//                                    "residents" to locationResidents.toTypedArray()
//                                )
//                            )
//                        }
//                    })
//
//                }
//            }
//            is ScreenState.Error -> {
//                pd.visibility = View.GONE
//                val view = pd.rootView
//                Snackbar.make(view, state.message.toString(), Snackbar.LENGTH_LONG).show()
//
//            }
//        }
//    }
