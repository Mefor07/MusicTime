package app.medrx.MedrxApp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ray.musictime.model.Albums
import com.ray.musictime.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

object AlbumRepository {
    val albums = MutableLiveData<Albums>()

    fun getItunesAlbums(): MutableLiveData<Albums> {
        val call = RetrofitClient.apiInterface.getAlbums()
        call.enqueue(object: Callback<Albums> {

            override fun onResponse(call: Call<Albums>, response: Response<Albums>) {
                val data = response.body()
                //val success = data!!.success
                //val product = data!!.products
                val feed = data?.feed
                albums.value = feed?.let { Albums(it) }
            }
            override fun onFailure(call: Call<Albums>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }
        })

        return albums

    }
}