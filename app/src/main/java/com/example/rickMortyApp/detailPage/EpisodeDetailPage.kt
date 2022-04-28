package com.example.rickMortyApp.detailPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.rickMortyApp.R
import com.example.rickMortyApp.databinding.FragmentFirstBinding
import com.example.rickMortyApp.viewmodel.MainViewModel


class EpisodeDetailPage : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val args: EpisodeDetailPageArgs by navArgs()
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_episode_detail_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageView>(R.id.image_arrow_episode).setOnClickListener{
            requireActivity().onBackPressed()
            //            requireActivity().setResult(Activity.RESULT_OK)
            //            requireActivity().finish()
        }


        val name = view.findViewById<TextView>(R.id.textEp_name)
        val airDate = view.findViewById<TextView>(R.id.textEp_airDate)
        val created = view.findViewById<TextView>(R.id.textEp_created)
        val url = view.findViewById<TextView>(R.id.textEp_url)
        val character = view.findViewById<TextView>(R.id.textEp_character)
        val episode = view.findViewById<TextView>(R.id.textEp_Episode)

        name.text =  args.name
        airDate.text = args.airDate
        created.text = args.created
        url.text = args.url
        character.text = args.character[0]
        episode.text = args.episode

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
//                    val adaptor = EpiDetailAdaptor(state.data)
//                    print(">> adaptor")
//                    val recyclerView = view?.findViewById<RecyclerView>(R.id.recycle_characters_in_episode_details)
//                    recyclerView?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
//                    recyclerView?.adapter = adaptor
//                    adaptor.setOnItemClickListener(object : EpiDetailAdaptor.OnItemClickListener{
//                        override fun onItemClick(position: Int) {
//                            println("get click")
//                        }
//                    })
//                }
//            } is ScreenState.Error -> {
//            }
//        }
//
//
//    }

}