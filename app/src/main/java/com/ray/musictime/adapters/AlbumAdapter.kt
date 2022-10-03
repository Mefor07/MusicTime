package com.ray.musictime.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ray.musictime.R
import com.ray.musictime.databinding.AlbumItemBinding
import com.ray.musictime.interfaces.AlbumClickListener
import com.ray.musictime.model.Albums
import com.ray.musictime.model.Result
import com.ray.musictime.view.fragments.FragmentHome
import com.squareup.picasso.Picasso

class AlbumAdapter(var context: FragmentHome, var itemList:List<Result>): RecyclerView.Adapter<AlbumAdapter.MyViewHolder>() {



    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = AlbumItemBinding.bind(view)
        var albumArt = binding.img
        var albumName = binding.albumName
        var artistName = binding.artistName
        var rView = binding.rel1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemList[position]
        //holder.albumArt.setImageResource(item)
        Picasso.get().load(item.artworkUrl100).transform(
            FragmentHome.RoundCornersTransform(
                20.0f
            )
        ).into(holder.albumArt)
        holder.albumName.text = item.name
        holder.artistName.text = item.artistName


        holder.rView.setOnClickListener {
            //onClickListener.categoryClick(itemList[position])
            context.albumClick(item)
        }


    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}