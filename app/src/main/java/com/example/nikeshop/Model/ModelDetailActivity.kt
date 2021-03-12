package com.example.nikeshop.Model

import android.app.Activity
import android.content.Context
import android.util.Log
import com.example.nikeshop.activities.LoginActivity
import com.example.nikeshop.adapter.RecyclerItemProductAdapter
import com.example.nikeshop.dataClass.DataComments
import com.example.nikeshop.dataClass.DataProduct
import com.example.nikeshop.dataClass.DataResponse
import com.example.nikeshop.enumration.TypeGetProduct
import com.example.nikeshop.net.ApiService
import com.example.nikeshop.net.CountryPresenterListener
import com.example.nikeshop.test.DataTest
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelDetailActivity(private val activity: Activity) : KoinComponent {

    private val apiService: ApiService by inject()

    // fun getTypeAsIntent() = activity.intent.getSerializableExtra(RecyclerItemProductAdapter.KEY_TYPE) as TypeGetProduct

    fun getObjectApi() = apiService

    fun getIdAsIntent() = activity.intent.getIntExtra(RecyclerItemProductAdapter.KEY_ID, 0)

    fun getTitleAsIntent() =
        activity.intent.getStringExtra(RecyclerItemProductAdapter.KEY_NAME) ?: ""

    fun getCommentsDataRecycler(countryPresenterListener: CountryPresenterListener<List<DataComments>>) =
        apiService.getApi().getProductComments(getIdAsIntent())
            .enqueue(object : Callback<List<DataComments>> {
                override fun onResponse(
                    call: Call<List<DataComments>>,
                    response: Response<List<DataComments>>
                ) {
                    val data = response.body()

                    if (data != null) {
                        if (data.isNotEmpty()) {
                            countryPresenterListener.onResponse(data)
                            countryPresenterListener.onEmptyResponse(false)
                        } else {
                            //countryPresenterListener.onFailure("comment data is null")
                            countryPresenterListener.onEmptyResponse(true)
                        }
                    }else{
                        countryPresenterListener.onFailure("خطا در دریافت اصلاعات از سرور")
                        countryPresenterListener.onEmptyResponse(false)
                    }
                }

                override fun onFailure(call: Call<List<DataComments>>, t: Throwable) {
                    countryPresenterListener.onFailure("خطا در دریافت اصلاعات از سرور")
                    countryPresenterListener.onEmptyResponse(false)
                }

            })

    fun getProductById(countryPresenterListener: CountryPresenterListener<DataProduct>) {

        Log.i("TEST", getIdAsIntent().toString())

        apiService.getApi().getProductById(getIdAsIntent())
            .enqueue(object : Callback<DataProduct> {
                override fun onResponse(
                    call: Call<DataProduct>,
                    response: Response<DataProduct>
                ) {
                    val data = response.body()

                    if (data != null)
                        countryPresenterListener.onResponse(data)
                    else
                        Log.e("ERROR_NULL_DATA", "product is null")

                }

                override fun onFailure(call: Call<DataProduct>, t: Throwable) {
                    Log.e("ERROR_FAILURE", "error in get Data ${t.message}")
                    countryPresenterListener.onFailure("خطا در دریافت اطلاعات از سرور")
                }
            })
    }

    fun getUserEmail() =
        activity.getSharedPreferences(LoginActivity.LOGIN_PREF, Context.MODE_PRIVATE)
            ?.getString(LoginActivity.USER_EMAIL, "default Email") ?: ""

    fun getIsProductYourFavourite(countryPresenterListener: CountryPresenterListener<DataResponse>) {


        apiService.getApi().isOnYourFavourite(getUserEmail(), getIdAsIntent())
            .enqueue(object : Callback<DataResponse> {
                override fun onResponse(
                    call: Call<DataResponse>,
                    response: Response<DataResponse>
                ) {
                    val data = response.body()

                    println("data isFav: ${data?.error} ${data?.error_msg}")

                    if (data != null) {
                        countryPresenterListener.onResponse(data)
                    } else {
                        countryPresenterListener.onFailure("null data for is favourite")
                        Log.e("IS_FAVOURITE_NULL", "null data for is favourite")
                    }
                }

                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    countryPresenterListener.onFailure("خطا در دریافت ایزعلاقه مندی")
                    Log.e("IS_FAVOURITE_NULL", "خطا در دریافت ایزعلاقه مندی")

                }

            })
    }
}