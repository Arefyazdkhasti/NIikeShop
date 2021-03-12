package com.example.nikeshop.Model

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.nikeshop.activities.LoginActivity
import com.example.nikeshop.dataClass.DataReceiver
import com.example.nikeshop.fragments.ShoppingCartFragment
import com.example.nikeshop.net.ApiService
import com.example.nikeshop.net.CountryPresenterListener
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelReceiverActivity(private val activity: AppCompatActivity) : KoinComponent {

    private val apiService: ApiService by inject()

    fun getEmail() =
        activity.getSharedPreferences(LoginActivity.LOGIN_PREF, Context.MODE_PRIVATE)
            .getString(LoginActivity.USER_EMAIL, "default Email") ?: ""

    fun getTotalPriceAsIntent() =
        activity.intent.getIntExtra(ShoppingCartFragment.KEY_TOTAL_PRICE, 0)

    fun getFinalPriceAsIntent() =
        activity.intent.getIntExtra(ShoppingCartFragment.KEY_FINAL_PRICE, 0)

    fun getReceiverData(countryPresenterListener: CountryPresenterListener<DataReceiver>) =
        apiService.getApi().getUserReceiver(getEmail())
            .enqueue(object : Callback<DataReceiver>{
                override fun onResponse(
                    call: Call<DataReceiver>,
                    response: Response<DataReceiver>
                ) {
                   val data=response.body()

                   if (data!=null){
                       countryPresenterListener.onEmptyResponse(false)
                       countryPresenterListener.onResponse(data)
                   }else{
                       countryPresenterListener.onEmptyResponse(true)
                       countryPresenterListener.onFailure("خطا در دریافت اطلاعات")
                   }
                }

                override fun onFailure(call: Call<DataReceiver>, t: Throwable) {
                    countryPresenterListener.onEmptyResponse(true)
                    countryPresenterListener.onFailure(" خطا در دریافت اطلاعات")
                    Log.i("GET_RECEIVER_DATA",t.message.toString())
                }
            })

    fun getApi() = apiService
}