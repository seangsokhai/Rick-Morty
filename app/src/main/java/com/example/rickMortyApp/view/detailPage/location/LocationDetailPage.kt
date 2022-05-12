package com.example.rickMortyApp.view.detailPage.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickMortyApp.R
import com.example.rickMortyApp.adaptor.EpisodeListCharacterAdaptor
import com.example.rickMortyApp.adaptor.ResidentAdaptor
import com.example.rickMortyApp.databinding.FragmentLocationDetailPageBinding
import com.example.rickMortyApp.network.ApiClient
import com.example.rickMortyApp.network.CharacterResponse
import com.example.rickMortyApp.viewmodel.CharacterViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LocationDetailPage : Fragment() {
    private val vm : CharacterViewModel by viewModels()
    private var _binding: FragmentLocationDetailPageBinding? = null
    private val arg : LocationDetailPageArgs by navArgs()
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLocationDetailPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageView>(R.id.image_arrow_location).setOnClickListener{
            requireActivity().onBackPressed()
//            requireActivity().setResult(Activity.RESULT_OK)
//            requireActivity().finish()
        }

        view.findViewById<TextView>(R.id.textLc_name).text =  arg.name
        view.findViewById<TextView>(R.id.textLc_created).text = arg.created
        view.findViewById<TextView>(R.id.textLc_url).text = arg.url
        view.findViewById<TextView>(R.id.textLc_dimension).text = arg.dimension
        view.findViewById<TextView>(R.id.textLc_type).text = arg.type

        val listResidents = arg.residents.toList().take(5)
        println(">>> listf $listResidents")
        for (i in listResidents) {
            val getID = i.split("/")[5].toInt()
            viewLifecycleOwner.lifecycleScope.launch {
                val client = ApiClient.apiService.fetchCharacter("$getID")
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
                            val adaptor = EpisodeListCharacterAdaptor(result!!)
                            val recyclerView = binding.recycleLocationDetailResidents
                            recyclerView.layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                            recyclerView.adapter = adaptor
                            adaptor.setOnItemClickListener(object : EpisodeListCharacterAdaptor.OnItemClickListener{
                                override fun onItemClick(position: Int) {
                                    findNavController().navigate(
                                    R.id.residentsDetail, bundleOf(
                                        "name" to result[position].name,
                                        "created" to result[position].created,
                                        "url" to result[position].url,
                                        "status" to result[position].status,
                                        "species" to result[position].species,
                                        "gender" to result[position].gender,
                                        "image" to result[position].image
                                    )
                                )
                                }
                            })

                        }
                    }
                })
            }
        }
    }
}



// convert array string to list
//        val listResidents = arg.residents.toList().map {
//            Residents(
//                name = it, gender = it,
//                species = null, type = null ,
//                status = it, origin = null,
//                location = null , image = it ,
//                episode = null , url = it,
//                created = it
//            )
//        }
//

//                    val adaptor = ResidentAdaptor(it!!.results)
//                    println(">>> re ${it.results}")
//                    val recyclerView = binding.recycleLocationDetailResidents
//                    recyclerView.layoutManager =
//                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
//                    recyclerView.adapter = adaptor
//                    adaptor.setOnItemClickListener(object : ResidentAdaptor.OnItemClickListener{
//                        override fun onItemClick(position: Int) {
//                                findNavController().navigate(
//                                    R.id.residentsDetail, bundleOf(
//                                        "name" to it.results[position].name,
//                                        "created" to it.results[position].created,
//                                        "url" to it.results[position].url,
//                                        "status" to it.results[position].status,
//                                        "species" to it.results[position].species,
//                                        "gender" to it.results[position].gender,
//                                        "image" to it.results[position].image
//                                    )
//                                )
//                        }
//                    })