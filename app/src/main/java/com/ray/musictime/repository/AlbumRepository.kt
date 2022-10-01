package app.medrx.MedrxApp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import app.medrx.MedrxApp.model.Order
import app.medrx.MedrxApp.model.ProductCategory
import app.medrx.MedrxApp.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

object CategoryRepository {
    val category = MutableLiveData<ProductCategory>()

    fun getProductByCategory(token:String, categoryId:String): MutableLiveData<ProductCategory> {
        val call = RetrofitClient.apiInterface.getProductByCategory(categoryId, token)
        call.enqueue(object: Callback<ProductCategory> {

            override fun onResponse(call: Call<ProductCategory>, response: Response<ProductCategory>) {
                val data = response.body()
                val success = data!!.success
                val product = data!!.products
                CategoryRepository.category.value = ProductCategory(success, product)
            }
            override fun onFailure(call: Call<ProductCategory>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }
        })

        return category

    }
}