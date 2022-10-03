package com.ray.musictime.interfaces
import com.ray.musictime.model.Albums
import com.ray.musictime.model.Result

interface AlbumClickListener {
    fun albumClick(albums: Result)
}