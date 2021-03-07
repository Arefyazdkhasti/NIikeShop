package com.example.nikeshop.Model

import android.app.Activity
import android.util.Log
import com.example.nikeshop.dataClass.DataQuestion
import com.example.nikeshop.net.ApiService
import com.example.nikeshop.net.CountryPresenterListener
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelQuestionActivity : KoinComponent {

    private val apiService: ApiService by inject()

    fun getDataQuestion(mListener: CountryPresenterListener<List<DataQuestion>>) {
        apiService.getApi().getDataQuestion()
            .enqueue(object : Callback<List<DataQuestion>> {

                override fun onResponse(
                    call: Call<List<DataQuestion>>,
                    response: Response<List<DataQuestion>>
                ) {
                    val data = response.body()

                    if (data != null)
                        mListener.onResponse(data)
                    else {
                        Log.e("ERROR_NULL_DATA", "questions are null")
                        mListener.onFailure("خطا از سمت سرور")
                    }
                }

                override fun onFailure(call: Call<List<DataQuestion>>, t: Throwable) {
                    Log.e("ERROR_FAILURE", "error in get Data ${t.message}")
                    mListener.onFailure("خطا در دریافت اطلاعات از سرور")
                }
            })
    }
}