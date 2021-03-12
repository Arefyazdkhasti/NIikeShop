package com.example.nikeshop.Model

import android.app.Activity
import android.util.Log
import com.example.nikeshop.adapter.RecyclerItemCategoryAdapter
import com.example.nikeshop.dataClass.DataProduct
import com.example.nikeshop.net.ApiService
import com.example.nikeshop.net.CountryPresenterListener
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelCategoryActivity(private val activity: Activity) : KoinComponent {

    private val apiService: ApiService by inject()

    private fun getIdAsIntent(): Int =
        activity.intent.getIntExtra(RecyclerItemCategoryAdapter.KET_CATEGORY_ID, 0)

    fun getTitleAsIntent() =
        activity.intent.getStringExtra(RecyclerItemCategoryAdapter.KET_CATEGORY_TITLE) ?: ""

    /*fun getDataNewProductsByID(countryPresenterListener: CountryPresenterListener<List<DataProduct>>){
        apiService.getApi().getDataNewProductsByCategoryId(getIdAsIntent())
            .enqueue(object : Callback<List<DataProduct>>{

                override fun onResponse(
                    call: Call<List<DataProduct>>,
                    response: Response<List<DataProduct>>
                ) {
                    val data = response.body()

                    if (data != null)
                        countryPresenterListener.onResponse(data)
                    else
                        Log.e("ERROR_NULL_DATA", "new product in category is null")
                }

                override fun onFailure(call: Call<List<DataProduct>>, t: Throwable) {
                    Log.e("ERROR_FAILURE", "error in get Data ${t.message}")
                    countryPresenterListener.onFailure(t.message.toString())
                }

            })
    }*/

    /*fun getDataTopSellingProductsByID(countryPresenterListener: CountryPresenterListener<List<DataProduct>>){
        apiService.getApi().getDataTopSellingByCategoryId(getIdAsIntent())
            .enqueue(object : Callback<List<DataProduct>>{

                override fun onResponse(
                    call: Call<List<DataProduct>>,
                    response: Response<List<DataProduct>>
                ) {
                    val data = response.body()

                    if (data != null)
                        countryPresenterListener.onResponse(data)
                    else
                        Log.e("ERROR_NULL_DATA", "top selling product in category is null")
                }

                override fun onFailure(call: Call<List<DataProduct>>, t: Throwable) {
                    Log.e("ERROR_FAILURE", "error in get Data ${t.message}")
                    countryPresenterListener.onFailure(t.message.toString())
                }

            })
    }*/

    fun getDataCategory(
        countryPresenterListener: CountryPresenterListener<List<DataProduct>>,
        sortBy: String
    ) =
        apiService.getApi().getDataForCategory(sortBy)
            .enqueue(object : Callback<List<DataProduct>> {
                override fun onResponse(
                    call: Call<List<DataProduct>>,
                    response: Response<List<DataProduct>>
                ) {
                    val data = response.body()

                    if (data != null)
                        countryPresenterListener.onResponse(data)
                    else
                        countryPresenterListener.onFailure("خطا در دریافت اطلاعات")
                }

                override fun onFailure(call: Call<List<DataProduct>>, t: Throwable) {
                    countryPresenterListener.onFailure(t.message.toString())
                }

            })

    fun getDataSearch(
        countryPresenterListener: CountryPresenterListener<List<DataProduct>>,
        string: String
    ) =
        apiService.getApi().search(string,"")
            .enqueue(object : Callback<List<DataProduct>> {
                override fun onResponse(
                    call: Call<List<DataProduct>>,
                    response: Response<List<DataProduct>>
                ) {
                    val data = response.body()

                    if (data != null)
                        countryPresenterListener.onResponse(data)
                    else
                        countryPresenterListener.onFailure("خطا در دریافت اطلاعات")
                }

                override fun onFailure(call: Call<List<DataProduct>>, t: Throwable) {
                    countryPresenterListener.onFailure(t.message.toString())
                }

            })
}