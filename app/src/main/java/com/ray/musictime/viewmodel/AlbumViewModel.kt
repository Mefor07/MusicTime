package com.ray.musictime.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.medrx.MedrxApp.repository.AlbumRepository
import com.ray.musictime.model.Albums

class AlbumViewModel(): ViewModel() {
    var albumsLiveData: MutableLiveData<Albums>? = null

    fun getProductCategory(token:String, categoryId: String): LiveData<Albums>? {
        albumsLiveData = AlbumRepository.getItunesAlbums()
        return albumsLiveData
    }
}