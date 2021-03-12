package com.example.nikeshop.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeshop.MainActivity
import com.example.nikeshop.Presenter.PresenterShoppingCartFragment
import com.example.nikeshop.R
import com.example.nikeshop.activities.DetailActivity
import com.example.nikeshop.activities.LoginActivity
import com.example.nikeshop.dataClass.DataCart
import com.example.nikeshop.dataClass.DataResponse
import com.example.nikeshop.net.ApiService
import com.example.nikeshop.utitlity.PicassoUtility
import com.valdesekamdem.library.mdtoast.MDToast
import kotlinx.android.synthetic.main.item_recycler_shop.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerShopAdapter(
    private val context: Context? = null,
    private val presenter: PresenterShoppingCartFragment,
    private val data: List<DataCart>
) :
    RecyclerView.Adapter<RecyclerShopAdapter.ShopViewHolder>(), KoinComponent {


    inner class ShopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), KoinComponent {

        private val mainActivity = MainActivity()
        private val apiService: ApiService by inject()
        private val picassoUtility: PicassoUtility by inject()
        private val img = itemView.img_product_shop_recycler
        private val txtName = itemView.txt_name_product_shop_recycler
        private val txtDiscount = itemView.product_discount_shop_recycler
        private val txtPrice = itemView.product_price_item_buy_detail
        private val valueSelector = itemView.valueSelector_shop_recycler
        private val removeItem = itemView.remove_product_shop_recycler

        fun setData(data: DataCart) {
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

            valueSelector.setValue(data.count)

            removeItem.setOnClickListener {

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
                                    //TODO how to update badge
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
            valueSelector.btnPlus.setOnClickListener {
                valueSelector.incrementValue()
                val count = valueSelector.getValue()
                if (count != data.count) {
                    apiService.getApi().updateProductCountInCart(getEmail(), data.id, count)
                        .enqueue(object : Callback<DataResponse> {
                            override fun onResponse(
                                call: Call<DataResponse>,
                                response: Response<DataResponse>
                            ) {
                                val data = response.body()

                                if (data != null && !data.error) {
                                    context?.toast(data.error_msg)
                                    presenter.onRefresh()
                                } else {
                                    context?.toast("خطایی پیش آمده است")
                                }
                            }

                            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                                context?.toast("خطا در ارسال اطلاعات به سرور")
                            }

                        })
                }
            }
            valueSelector.btnMinus.setOnClickListener {
                valueSelector.decrementValue()
                val count = valueSelector.getValue()
                if (count != data.count) {
                    apiService.getApi().updateProductCountInCart(getEmail(), data.id, count)
                        .enqueue(object : Callback<DataResponse> {
                            override fun onResponse(
                                call: Call<DataResponse>,
                                response: Response<DataResponse>
                            ) {
                                val data = response.body()

                                if (data != null && !data.error) {
                                    MDToast.makeText(context,data.error_msg,MDToast.TYPE_SUCCESS)
                                    presenter.onRefresh()
                                } else {
                                    context?.toast("خطایی پیش آمده است")
                                }
                            }

                            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                                context?.toast("خطا در ارسال اطلاعات به سرور")
                            }

                        })
                }
            }

            img.setOnClickListener {
                context?.startActivity<DetailActivity>(
                    RecyclerItemProductAdapter.KEY_ID to data.id,
                    RecyclerItemProductAdapter.KEY_NAME to data.title
                )
            }
        }
    }

    private fun getEmail() =
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