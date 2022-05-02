package com.example.rickMortyApp.view.detailPage.episode

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
import com.example.rickMortyApp.adaptor.EpisodeListCharacterAdaptor

import com.example.rickMortyApp.databinding.FragmentEpisodeDetailPageBinding
import com.example.rickMortyApp.network.ApiClient
import com.example.rickMortyApp.network.CharacterResponse

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EpisodeDetailPage : Fragment() {
    private var _binding: FragmentEpisodeDetailPageBinding? = null
    private val args: EpisodeDetailPageArgs by navArgs()
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEpisodeDetailPageBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageView>(R.id.image_arrow_episode).setOnClickListener{
            requireActivity().onBackPressed()
            //            requireActivity().setResult(Activity.RESULT_OK)
            //            requireActivity().finish()
        }
        view.findViewById<TextView>(R.id.textEp_name).text =  args.name
        view.findViewById<TextView>(R.id.textEp_airDate).text = args.airDate
        view.findViewById<TextView>(R.id.textEp_created).text = args.created
        view.findViewById<TextView>(R.id.textEp_url).text = args.url
        view.findViewById<TextView>(R.id.textEp_Episode).text = args.episode

        val listCharacter = args.character.toList().take(5)
        println(">> size ${listCharacter}")


        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO){
            for (i in listCharacter){
                val getID = i.split("/")[5]
                println(">>>>>>>> $getID")
                val client = ApiClient.apiService.fetchSingleCharacter("$getID")
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
                            val recyclerView = binding.recycleCharactersInEpisodeDetails
                            recyclerView.layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                            recyclerView.adapter = adaptor
                            adaptor.setOnItemClickListener(object : EpisodeListCharacterAdaptor.OnItemClickListener{
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

