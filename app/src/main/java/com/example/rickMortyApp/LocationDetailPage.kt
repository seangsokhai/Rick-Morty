package com.example.rickMortyApp

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.rickMortyApp.databinding.FragmentFirstBinding
import com.example.rickMortyApp.viewmodel.MainViewModel

class LocationDetailPage : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val arg : LocationDetailPageArgs by navArgs()
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location_detail_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageView>(R.id.image_arrow).setOnClickListener{
            requireActivity().onBackPressed()
//            requireActivity().setResult(Activity.RESULT_OK)
//            requireActivity().finish()
        }

        val name = view.findViewById<TextView>(R.id.textLc_name)
        val created = view.findViewById<TextView>(R.id.textLc_created)
        val url = view.findViewById<TextView>(R.id.textLc_url)
        val dimension = view.findViewById<TextView>(R.id.textLc_dimension)
        val type = view.findViewById<TextView>(R.id.textLc_type)
        val residents = view.findViewById<TextView>(R.id.textLc_residents)

        name.text =  arg.name
        created.text = arg.created
        url.text = arg.url
        dimension.text = arg.dimension
        type.text = arg.type
        residents.text = arg.residents[0]

//        viewModel.episodeLiveData.observe(viewLifecycleOwner) { state ->
//            processRecyclerCharacter(state)
//        }

    }
//    private fun processRecyclerCharacter(state: ScreenState<List<EpisodeData>?>){
//
//        when(state){
//            is ScreenState.Loading ->{
//            }
//            is ScreenState.Success -> {
//                if (state.data != null){
//                    val adaptor = EpisodeAdaptor(state.data)
//                    val recyclerView = view?.findViewById<RecyclerView>(R.id.recycle_location_detail)
//                    recyclerView?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
//                    recyclerView?.adapter = adaptor
//                    adaptor.setOnItemClickListener(object : EpisodeAdaptor.OnItemClickListener{
//                        override fun onItemClick(position: Int) {
//                            println("get click")
//                        }
//                    })
//                }
//            } is ScreenState.Error -> {
//        }
//        }
//
//
//    }


}