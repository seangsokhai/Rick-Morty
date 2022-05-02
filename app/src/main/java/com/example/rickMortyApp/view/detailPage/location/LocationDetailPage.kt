package com.example.rickMortyApp.view.detailPage.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickMortyApp.R
import com.example.rickMortyApp.adaptor.ResidentAdaptor
import com.example.rickMortyApp.databinding.FragmentLocationDetailPageBinding
import com.example.rickMortyApp.network.ApiClient
import com.example.rickMortyApp.network.CharacterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LocationDetailPage : Fragment() {
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

        println(">> size ${listResidents}")

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            for (i in listResidents){
                val getLastID = i.split("/")[5]
                println(">>>>>>>> $getLastID")
                val client = ApiClient.apiService.fetchSingleCharacter("$getLastID")
                client.enqueue(object : Callback<CharacterResponse> {
                    override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {1
                        throw(Error("Character: ${t.message.toString()}"))
                    }
                    override fun onResponse(
                        call: Call<CharacterResponse>,
                        response: Response<CharacterResponse>
                    ) {
                        if (response.isSuccessful){
                            val result = response.body()?.results
                            val adaptor = ResidentAdaptor(result!!)
                            val recyclerView = binding.recycleLocationDetailResidents
                            recyclerView.layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                            recyclerView.adapter = adaptor
                            adaptor.setOnItemClickListener(object : ResidentAdaptor.OnItemClickListener{
                                override fun onItemClick(position: Int) {
                                    val name = result[position].name
                                    val created = result[position].created
                                    val url = result[position].url
                                    val status = result[position].status
                                    val species = result[position].species
                                    val gender = result[position].gender
                                    val image = result[position].image

                                    findNavController().navigate(
                                        R.id.residentsDetail, bundleOf(
                                            "name" to name,
                                            "created" to created,
                                            "url" to url,
                                            "status" to status,
                                            "species" to species,
                                            "gender" to gender,
                                            "image" to image
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


// create value
// send data to API
// get from api
// show in view



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