package com.example.nikeshop.Model

import android.content.Context
import com.example.nikeshop.activities.LoginActivity
import com.example.nikeshop.dataClass.DataResponse
import com.example.nikeshop.fragments.AccountFragment
import com.example.nikeshop.fragments.HomeFragment
import com.example.nikeshop.fragments.ShoppingCartFragment
import com.example.nikeshop.net.ApiService
import com.example.nikeshop.net.CountryPresenterListener
import org.koin.standalone.KoinComponent
import org.koin.standalone.get
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelMainActivity(private val context: Context) : KoinComponent {

    private val apiService: ApiService by inject()
    private val homeFragment: HomeFragment by inject()
    private val accountFragment: AccountFragment by inject()
    private val shopFragment: ShoppingCartFragment by inject()

    companion object {

        const val KEY_HOME_FRAGMENT = "homeFragment"
        const val KEY_ACCOUNT_FRAGMENT = "accountFragment"
        const val KEY_SHOPPING_CART_FRAGMENT = "shoppingCartFragment"

    }


    fun getItemChecked() = 2

    fun getMainFragment() = homeFragment

    fun getAllFragments() = mapOf(
        KEY_HOME_FRAGMENT to homeFragment,
        KEY_ACCOUNT_FRAGMENT to accountFragment,
        KEY_SHOPPING_CART_FRAGMENT to shopFragment
    )

    private fun getEmail() = context.getSharedPreferences(LoginActivity.LOGIN_PREF, Context.MODE_PRIVATE)
        .getString(LoginActivity.USER_EMAIL, "default Email") ?: ""

    fun getCartSize(countryPresenterListener: CountryPresenterListener<DataResponse>) =
        apiService.getApi().getCartSize(getEmail())
            .enqueue(object : Callback<DataResponse> {
                override fun onResponse(
                    call: Call<DataResponse>,
                    response: Response<DataResponse>
                ) {
                    val data = response.body()

                    if (data != null)
                        countryPresenterListener.onResponse(data)
                    else
                        countryPresenterListener.onFailure("سبد خرید خالی است")
                }

                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    countryPresenterListener.onFailure(t.message.toString())
                }

            })


}