package com.ray.musictime.view.fragments

import android.graphics.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ray.musictime.R
import com.ray.musictime.adapters.AlbumAdapter
import com.ray.musictime.databinding.FragmentHomeBinding
import com.ray.musictime.interfaces.AlbumClickListener
import com.ray.musictime.model.Albums
import com.ray.musictime.model.Result
import com.ray.musictime.view.MainActivity
import com.ray.musictime.viewmodel.AlbumViewModel
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentHome() : Fragment(), AlbumClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var cont = context

    lateinit var albumViewModel: AlbumViewModel
    private val albumList = ArrayList<Result>()
    private lateinit var albumAdapter: AlbumAdapter

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
        val binding = FragmentHomeBinding.inflate(layoutInflater)

        //Picasso.get().load("https://is5-ssl.mzstatic.com/image/thumb/Music112/v4/3e/04/eb/3e04ebf6-370f-f59d-ec84-2c2643db92f1/196626945068.jpg/100x100bb.jpg").transform(RoundCornersTransform(20.0f)).into(binding.img)


        albumViewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)
        albumViewModel.getItunesAlbum()!!.observe(requireActivity(), Observer {
            Log.d("MTIME", it.toString())

            for(item in it.feed.results)
              albumList.add(item)

            Log.d("SIZE", ""+albumList.size)
            val albumLayoutManager = GridLayoutManager(context, 2)
            val albumListRecyclerView: RecyclerView = binding.albumList
            albumListRecyclerView.layoutManager = albumLayoutManager
            albumAdapter = AlbumAdapter(this@FragmentHome, albumList);
            albumListRecyclerView.adapter = albumAdapter
            albumAdapter.notifyDataSetChanged()
        })
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



    class RoundCornersTransform(private val radiusInPx: Float) : Transformation {

        override fun transform(source: Bitmap): Bitmap {
            val bitmap = Bitmap.createBitmap(source.width, source.height, source.config)
            val canvas = Canvas(bitmap)
            val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
            val shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            paint.shader = shader
            val rect = RectF(0.0f, 0.0f, source.width.toFloat(), source.height.toFloat())
            canvas.drawRoundRect(rect, radiusInPx, radiusInPx, paint)
            source.recycle()

            return bitmap
        }

        override fun key(): String {
            return "round_corners"
        }

    }

    override fun albumClick(albums: Result) {

        var arrGenre: ArrayList<String> = ArrayList();

        //extract each genre into a simple string array
        for(genreItem in albums.genres){
            arrGenre.add(genreItem.name)
        }
        val detailFragment = FragmentDetail()
        val bundle = Bundle()
        bundle.putString("IMAGE", albums.artworkUrl100)
        bundle.putString("ARTIST", albums.artistName)
        bundle.putString("ALBUM", albums.name)
        bundle.putStringArrayList("GENRE", arrGenre)
        detailFragment.arguments = bundle
        this.setCurrentFragment(detailFragment)

        //Toast.makeText(context, "Hey", Toast.LENGTH_LONG).show()
    }


    private fun setCurrentFragment(fragment: Fragment) =
        activity?.supportFragmentManager?.beginTransaction().apply {
            this!!.replace(R.id.frag_container,fragment)
            commit()
        }




}