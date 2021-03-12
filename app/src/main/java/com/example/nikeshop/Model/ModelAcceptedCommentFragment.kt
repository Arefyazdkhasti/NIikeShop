package com.example.nikeshop.Model

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.nikeshop.activities.LoginActivity
import com.example.nikeshop.dataClass.DataComments
import com.example.nikeshop.dataClass.DataProduct
import com.example.nikeshop.dataClass.DataYourComment
import com.example.nikeshop.net.ApiService
import com.example.nikeshop.net.CountryPresenterListener
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelAcceptedCommentFragment(private val context: Context? = null) : KoinComponent {

    private val apiService: ApiService by inject()

    private fun getEmail() =
        context?.getSharedPreferences(LoginActivity.LOGIN_PREF, Context.MODE_PRIVATE)
            ?.getString(LoginActivity.USER_EMAIL, "default Email") ?: ""

    fun getData(
        countryPresenterListener: CountryPresenterListener<List<DataYourComment>>,
        status: Int
    ) = apiService.getApi().getUserComments(getEmail(), status)
            .enqueue(object : Callback<List<DataYourComment>> {
                override fun onResponse(
                    call: Call<List<DataYourComment>>,
                    response: Response<List<DataYourComment>>
                ) {
                    Log.i("EMAIL_COMMENT", getEmail())

                    val data = response.body()

                    if (data != null) {
                        if (data.isNotEmpty()) {
                            countryPresenterListener.onResponse(data)
                            countryPresenterListener.onEmptyResponse(false)
                        } else {
                            countryPresenterListener.onFailure("نظرات تایید شده شما خالی است")
                            countryPresenterListener.onEmptyResponse(true)
                        }
                    }
                }

                override fun onFailure(call: Call<List<DataYourComment>>, t: Throwable) {
                    countryPresenterListener.onFailure("نظرات تایید شده شما خالی است")
                    countryPresenterListener.onEmptyResponse(true)
                }


            })


}