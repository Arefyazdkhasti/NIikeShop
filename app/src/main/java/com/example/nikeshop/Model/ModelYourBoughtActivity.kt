package com.example.nikeshop.Model

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.nikeshop.activities.LoginActivity
import com.example.nikeshop.dataClass.DataFormerBasket
import com.example.nikeshop.dataClass.DataBoughtDetail
import com.example.nikeshop.net.ApiService
import com.example.nikeshop.net.CountryPresenterListener
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelYourBoughtActivity(private val activity: AppCompatActivity) : KoinComponent {

    private val apiService: ApiService by inject()

    fun getEmail() =
        activity.getSharedPreferences(LoginActivity.LOGIN_PREF, Context.MODE_PRIVATE)
            .getString(LoginActivity.USER_EMAIL, "default Email") ?: ""


    //TODO dynamic basket_id
    fun getBoughtData(countryPresenterListener: CountryPresenterListener<List<DataBoughtDetail>>) =
        apiService.getApi().getUserFormerBought(getEmail(),"b7677f82-e75f-46c8-a6ba-ca2f9b56d752")
            .enqueue(object :Callback<List<DataBoughtDetail>>{
                override fun onResponse(
                    call: Call<List<DataBoughtDetail>>,
                    response: Response<List<DataBoughtDetail>>
                ) {
                    val data=response.body()

                    if (data!=null){
                        if (data.isNotEmpty()) {
                            countryPresenterListener.onResponse(data)
                            countryPresenterListener.onEmptyResponse(false)
                        }else{
                            countryPresenterListener.onEmptyResponse(true)
                        }
                    }else{
                        countryPresenterListener.onEmptyResponse(true)
                        countryPresenterListener.onFailure("خطا در دریافت داده ها")
                    }
                }

                override fun onFailure(call: Call<List<DataBoughtDetail>>, t: Throwable) {
                    countryPresenterListener.onEmptyResponse(true)
                    countryPresenterListener.onFailure("خطا در دریافت داده ها")
                   Log.i("BOUGHT_DATA",t.message.toString())
                }
            })

    fun getBasketIdData(countryPresenterListener: CountryPresenterListener<List<DataFormerBasket>>) =
        apiService.getApi().getUserFormerBasket(getEmail())
            .enqueue(object :Callback<List<DataFormerBasket>>{
                override fun onResponse(
                    call: Call<List<DataFormerBasket>>,
                    response: Response<List<DataFormerBasket>>
                ) {
                    val data=response.body()

                    if (data!=null){
                        if (data.isNotEmpty()) {
                            countryPresenterListener.onResponse(data)
                            countryPresenterListener.onEmptyResponse(false)
                        }else{
                            countryPresenterListener.onEmptyResponse(true)
                        }
                    }else{
                        countryPresenterListener.onEmptyResponse(true)
                        countryPresenterListener.onFailure("خطا در دریافت داده ها")
                    }
                }

                override fun onFailure(call: Call<List<DataFormerBasket>>, t: Throwable) {
                    countryPresenterListener.onEmptyResponse(true)
                    countryPresenterListener.onFailure("خطا در دریافت داده ها")
                    Log.i("BOUGHT_DATA",t.message.toString())
                }

            })

}