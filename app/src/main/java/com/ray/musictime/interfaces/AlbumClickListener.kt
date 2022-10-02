package com.ray.musictime.interfaces
import com.ray.musictime.model.Albums

interface AlbumClickListener {
    fun categoryClick(albums: Albums)
}