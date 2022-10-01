package com.ray.musictime.repository

import com.ray.musictime.model.Albums
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @Headers(
        "Accept: application/json",
    )
    @GET("v2/us/music/most-played/10/albums.json")
    fun getAlbums(
        //@Header("Authorization") token: String?,
        //@Path("id") id: String
    ):Call<Albums>

}
