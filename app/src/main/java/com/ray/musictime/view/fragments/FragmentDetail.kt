package com.ray.musictime.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ray.musictime.adapters.GenreAdapter
import com.ray.musictime.databinding.FragmentDetailBinding
import com.ray.musictime.model.Genre
import com.squareup.picasso.Picasso


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentDetail() : Fragment(){
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val genreList = ArrayList<Genre>()
    private lateinit var genreAdapter: GenreAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentDetailBinding.inflate(layoutInflater)

        Picasso.get().load(arguments?.getString("IMAGE")).into(binding.albumImage)
        binding.artistName.text =arguments?.getString("ARTIST")
        binding.albumName.text =arguments?.getString("ALBUM")

        val genreLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val geListRecyclerView: RecyclerView = binding.genreRecyclerview
        geListRecyclerView.layoutManager = genreLayoutManager
        genreAdapter = GenreAdapter(this@FragmentDetail, arguments?.getStringArrayList("GENRE"));
        geListRecyclerView.adapter = genreAdapter
        genreAdapter.notifyDataSetChanged()

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentHome.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentHome().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



}