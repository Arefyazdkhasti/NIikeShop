package com.example.nikeshop.Model

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.nikeshop.activities.LoginActivity
import com.example.nikeshop.dataClass.DataCart
import com.example.nikeshop.dataClass.DataProduct
import com.example.nikeshop.net.ApiService
import com.example.nikeshop.net.CountryPresenterListener
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ModelShoppingCartFragment(private val context: Context? = null) : KoinComponent {


    private val apiService: ApiService by inject()


    private fun getEmail() = context?.getSharedPreferences(LoginActivity.LOGIN_PREF, Context.MODE_PRIVATE)
        ?.getString(LoginActivity.USER_EMAIL, "default Email") ?: ""

    fun getShopProduct(
        countryPresenterListener: CountryPresenterListener<List<DataCart>>,
    ) {
        apiService.getApi().showCart(getEmail())
            .enqueue(object : Callback<List<DataCart>> {
                override fun onResponse(
                    call: Call<List<DataCart>>,
                    response: Response<List<DataCart>>
                ) {
                    val data = response.body()

                    if (data != null) {
                        countryPresenterListener.onResponse(data)
                        countryPresenterListener.onEmptyResponse(false)
                    } else {
                        countryPresenterListener.onFailure("سبد خرید شما خالی است")
                        countryPresenterListener.onEmptyResponse(true)
                    }
                }

                override fun onFailure(call: Call<List<DataCart>>, t: Throwable) {
                    countryPresenterListener.onFailure("سبد خرید شما خالی است")
                    countryPresenterListener.onEmptyResponse(true)
                }

            })
    }

}