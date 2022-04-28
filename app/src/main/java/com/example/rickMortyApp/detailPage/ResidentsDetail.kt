package com.example.rickMortyApp.detailPage

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickMortyApp.R
import com.example.rickMortyApp.adaptor.ResidentsAdaptor
import com.example.rickMortyApp.databinding.FragmentFirstBinding
import com.example.rickMortyApp.network.Residents
import com.example.rickMortyApp.viewmodel.MainViewModel

class ResidentsDetail : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val args : ResidentsDetailArgs by navArgs()
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_residents_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageView>(R.id.image_arrow_Resident).setOnClickListener{
            requireActivity().onBackPressed()
//            requireActivity().setResult(Activity.RESULT_OK)
//            requireActivity().finish()
        }

        val created = view.findViewById<TextView>(R.id.resident_created)
        val gender = view.findViewById<TextView>(R.id.resident_gender)
        val image = view.findViewById<ImageView>(R.id.resident_image)
        val name = view.findViewById<TextView>(R.id.resident_name)
        val type = view.findViewById<TextView>(R.id.resident_species)
        val status = view.findViewById<TextView>(R.id.resident_status)
        val url = view.findViewById<TextView>(R.id.resident_url)

        created.text = args.created
        gender.text = args.gender
        image.load(args.image){
            transformations()
        }
        name.text = args.name
        status.text = args.status
        url.text = args.url

    }

}