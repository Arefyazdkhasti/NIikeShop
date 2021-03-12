package com.example.nikeshop.Model

import android.util.Log
import com.example.nikeshop.dataClass.DataCategory
import com.example.nikeshop.dataClass.DataImageUrl
import com.example.nikeshop.dataClass.DataProduct
import com.example.nikeshop.net.ApiService
import com.example.nikeshop.net.CountryPresenterListener
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelHomeFragment :KoinComponent{

    private val apiService: ApiService by inject()

    //fun getDataRecycler() = DataTest.getDataProduct()

    fun getDataNewProducts(mListener: CountryPresenterListener<List<DataProduct>>){
        apiService.getApi().getNewProducts()
            .enqueue(object :Callback<List<DataProduct>>{
                override fun onResponse(
                    call: Call<List<DataProduct>>,
                    response: Response<List<DataProduct>>
                ) {
                    val data = response.body()

                    if (data != null)
                        mListener.onResponse(data)
                    else
                        Log.e("ERROR_NULL_DATA", "new product is null")
                }

                override fun onFailure(call: Call<List<DataProduct>>, t: Throwable) {
                    Log.e("ERROR_FAILURE", "error in get Data ${t.message}")
                    mListener.onFailure("خطا در دریافت اطلاعات از سرور")
                }

            })
    }

    fun getDataTopSellingProducts(mListener: CountryPresenterListener<List<DataProduct>>){
        apiService.getApi().getTopSellingProducts()
            .enqueue(object :Callback<List<DataProduct>>{
                override fun onResponse(
                    call: Call<List<DataProduct>>,
                    response: Response<List<DataProduct>>
                ) {
                    val data = response.body()

                    if (data != null)
                        mListener.onResponse(data)
                    else
                        Log.e("ERROR_NULL_DATA", "new top selling is null")
                }

                override fun onFailure(call: Call<List<DataProduct>>, t: Throwable) {
                    Log.e("ERROR_FAILURE", "error in get Data ${t.message}")
                    mListener.onFailure("خطا در دریافت اطلاعات از سرور")
                }

            })
    }

    fun getCategoryDataRecycler(mListener: CountryPresenterListener<List<DataCategory>>){
        apiService.getApi().getDataCategory()
            .enqueue(object :Callback<List<DataCategory>>{
                override fun onResponse(
                    call: Call<List<DataCategory>>,
                    response: Response<List<DataCategory>>
                ) {
                    val data = response.body()

                    if (data != null)
                        mListener.onResponse(data)
                    else
                        Log.e("ERROR_NULL_DATA", "category is null")
                }

                override fun onFailure(call: Call<List<DataCategory>>, t: Throwable) {
                    Log.e("ERROR_FAILURE", "error in get Data ${t.message}")
                    mListener.onFailure("خطا در دریافت اطلاعات از سرور")
                }

            })
    }

    fun getImageUrlForBanner(mListener: CountryPresenterListener<DataImageUrl>) {

        apiService.getApi().getImagesUrlForBanners()
            .enqueue(object : Callback<DataImageUrl> {
                override fun onResponse(
                    call: Call<DataImageUrl>,
                    response: Response<DataImageUrl>
                ) {
                    val data = response.body()

                    if (data != null)
                        mListener.onResponse(data)
                    else
                        Log.e("ERROR_NULL_DATA", "banner is null")

                }

                override fun onFailure(call: Call<DataImageUrl>, t: Throwable) {

                    Log.e("ERROR_FAILURE", "error in get Data ${t.message}")
                    mListener.onFailure("خطا در دریافت اطلاعات از سرور")

                }

            })
    }

}