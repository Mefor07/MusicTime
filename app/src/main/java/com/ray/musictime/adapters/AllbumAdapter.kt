package com.ray.musictime.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ray.musictime.R
import com.ray.musictime.databinding.AlbumItemBinding
import com.ray.musictime.interfaces.AlbumClickListener
import com.ray.musictime.model.Albums
import com.ray.musictime.view.fragments.FragmentHome
import com.squareup.picasso.Picasso

class CategoryAdapter(var itemList:List<Albums>,  onClickListener: AlbumClickListener): RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    var onClickListener = onClickListener

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = AlbumItemBinding.bind(view)
        var albumArt = binding.img
        var albumName = binding.albumName
        var artistName = binding.artistName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemList[position].feed
        //holder.albumArt.setImageResource(item)
        Picasso.get().load(item.results[position].artworkUrl100).transform(
            FragmentHome.RoundCornersTransform(
                20.0f
            )
        ).into(holder.albumArt)
        holder.albumName.text = item.results[position].name
        holder.artistName.text = item.results[position].artistName


        holder.albumArt.setOnClickListener {
            onClickListener.categoryClick(itemList[position])
        }


    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}