package com.example.nikeshop.Model

import android.app.Activity
import android.util.Log
import com.example.nikeshop.View.CoustomView.ProductView
import com.example.nikeshop.dataClass.DataProduct
import com.example.nikeshop.enumration.TypeGetProduct
import com.example.nikeshop.net.ApiService
import com.example.nikeshop.net.CountryPresenterListener
import com.example.nikeshop.test.DataTest
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelArchiveActivity(private val activity: Activity) : KoinComponent {

    private val apiService: ApiService by inject()

    //fun getDataInRecycler() = DataTest.getDataProduct()

    fun getTitleAsIntent() = activity.intent.getStringExtra(ProductView.TITLE_KEY) ?: ""

    fun getTypeProductAsIntent() = activity.intent.getSerializableExtra(ProductView.TYPE_KEY) as TypeGetProduct


    fun getDataNewProducts(mListener: CountryPresenterListener<List<DataProduct>>){
        apiService.getApi().getNewProducts()
            .enqueue(object : Callback<List<DataProduct>> {
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
            .enqueue(object : Callback<List<DataProduct>> {
                override fun onResponse(
                    call: Call<List<DataProduct>>,
                    response: Response<List<DataProduct>>
                ) {
                    val data = response.body()

                    if (data != null)
                        mListener.onResponse(data)
                    else
                        Log.e("ERROR_NULL_DATA", "top selling is null")
                }

                override fun onFailure(call: Call<List<DataProduct>>, t: Throwable) {
                    Log.e("ERROR_FAILURE", "error in get Data ${t.message}")
                    mListener.onFailure("خطا در دریافت اطلاعات از سرور")
                }

            })
    }



}