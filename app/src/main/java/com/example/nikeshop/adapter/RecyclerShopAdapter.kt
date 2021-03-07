package com.example.nikeshop.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeshop.Presenter.PresenterShoppingCartFragment
import com.example.nikeshop.R
import com.example.nikeshop.activities.LoginActivity
import com.example.nikeshop.dataClass.DataProduct
import com.example.nikeshop.dataClass.DataResponse
import com.example.nikeshop.net.ApiService
import com.example.nikeshop.utitlity.PicassoUtility
import kotlinx.android.synthetic.main.item_recycler_shop.view.*
import org.jetbrains.anko.toast
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerShopAdapter(
    private val context: Context? = null,
    private val presenter: PresenterShoppingCartFragment,
    private val data: List<DataProduct>
) :
    RecyclerView.Adapter<RecyclerShopAdapter.ShopViewHolder>(), KoinComponent {


    inner class ShopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), KoinComponent {

        private val apiService: ApiService by inject()
        private val picassoUtility: PicassoUtility by inject()
        private val img = itemView.img_product_shop_recycler
        private val txtName = itemView.txt_name_product_shop_recycler
        private val txtDiscount = itemView.product_discount_shop_recycler
        private val txtPrice = itemView.product_price_shop_recycler
        private val valueSelector = itemView.valueSelector_shop_recycler
        private val removeItem = itemView.remove_product_shop_recycler

        fun setData(data: DataProduct) {
            picassoUtility.setImage(data.imgAddress, img)
            txtName.text = data.title

            if (data.discount == 1) {
                txtDiscount.visibility = View.VISIBLE
                itemView.toman.visibility = View.VISIBLE
                txtPrice.text = data.priceDiscount
                txtDiscount.setCustomText(data.price)
            } else {
                txtDiscount.visibility = View.GONE
                itemView.toman.visibility = View.GONE
                txtPrice.text = data.price
            }

            valueSelector.setValue(1)

            removeItem.setOnClickListener {


                //TODO remove with user email ?
                println("user email for remove item from cart -> ${getEmail()}")


                apiService.getApi().removeProduct(getEmail(), data.id)
                    .enqueue(object : Callback<DataResponse> {
                        override fun onResponse(
                            call: Call<DataResponse>,
                            response: Response<DataResponse>
                        ) {
                            val item = response.body()

                            if (item != null) {
                                if (!item.error) {
                                    Log.i("REMOVE_PRODUCT", item.error_msg)
                                    context?.toast(item.error_msg)
                                    presenter.onRefresh()
                                } else {
                                    context?.toast(item.error_msg)
                                    Log.e("REMOVE_PRODUCT_E_TRUE", item.error_msg)
                                }
                            } else {
                                Log.e("REMOVE_PRODUCT_NULL", "unKnown error")
                            }
                        }

                        override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                            context?.toast(t.message.toString())
                            Log.e("ERROR_REMOVE_PRODUCT", t.message.toString())
                        }
                    })
            }

        }
    }

    private fun getEmail()=
        context?.getSharedPreferences(LoginActivity.LOGIN_PREF, Context.MODE_PRIVATE)
        ?.getString(LoginActivity.USER_EMAIL, "default Email") ?: ""


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ShopViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_recycler_shop,
                parent,
                false
            )
    )

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        holder.setData(data[position])
    }

    override fun getItemCount(): Int = data.size
}