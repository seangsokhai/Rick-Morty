package com.example.rickMortyApp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rickMortyApp.adaptor.LocationAdaptor
import com.example.rickMortyApp.databinding.FragmentFirstBinding
import com.example.rickMortyApp.databinding.FragmentLocationPageBinding
import com.example.rickMortyApp.network.LocationData
import com.example.rickMortyApp.ulti.ScreenState
import com.example.rickMortyApp.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class LocationPage : Fragment() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private var _binding: FragmentLocationPageBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationPageBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_location_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.locationLiveData.observe(viewLifecycleOwner) { state ->
            processLocationResponse(state)
        }
    }
    private fun processLocationResponse(state: ScreenState<List<LocationData>?>){
        val pd = binding.processBarLocationPage
        when(state){
            is ScreenState.Loading ->{
                pd.visibility = View.VISIBLE
            }
            is ScreenState.Success -> {

                if (state.data != null){

                    val adaptor = LocationAdaptor(state.data)
                    println(">>>>>>>>$adaptor")
                    val recyclerView = view?.findViewById<RecyclerView>(R.id.recycle_location_items)
                    recyclerView?.layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    recyclerView?.adapter = adaptor

                    adaptor.setOnItemClickListener(object : LocationAdaptor.OnItemClickListener{
                        override fun onItemClick(position: Int) {
                            val locationName = state.data[position].name
                            val locationCreated = state.data[position].created
                            val locationUrl = state.data[position].url
                            val locationType = state.data[position].type
                            val locationDimension = state.data[position].dimension


                            val locationResidents = state.data[position].residents

                            findNavController().navigate(R.id.locationDetailPage, bundleOf(
                                "name" to locationName,
                                "created" to locationCreated,
                                "url" to locationUrl,
                                "type" to locationType,
                                "dimension" to locationDimension,
                                "residents" to locationResidents.toTypedArray()
                                )
                            )
                        }
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