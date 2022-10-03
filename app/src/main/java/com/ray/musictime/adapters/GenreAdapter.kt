package com.ray.musictime.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ray.musictime.R
import com.ray.musictime.databinding.AlbumItemBinding
import com.ray.musictime.databinding.GenreItemBinding
import com.ray.musictime.interfaces.AlbumClickListener
import com.ray.musictime.model.Albums
import com.ray.musictime.model.Genre
import com.ray.musictime.model.Result
import com.ray.musictime.view.fragments.FragmentDetail
import com.ray.musictime.view.fragments.FragmentHome
import com.squareup.picasso.Picasso
import java.util.ArrayList

class GenreAdapter(var context: FragmentDetail, var itemList: ArrayList<String>?): RecyclerView.Adapter<GenreAdapter.MyViewHolder>() {



    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = GenreItemBinding.bind(view)
        var genreName = binding.genreName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.genre_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemList?.get(position)
        //holder.albumArt.setImageResource(item)

        holder.genreName.text = item



    }

    override fun getItemCount(): Int {
        return itemList!!.size
    }
}