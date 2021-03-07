package com.example.nikeshop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nikeshop.Model.ModelShoppingCartFragment
import com.example.nikeshop.Presenter.PresenterShoppingCartFragment
import com.example.nikeshop.R
import com.example.nikeshop.activities.ReceiverActivity
import com.example.nikeshop.adapter.RecyclerShopAdapter
import com.example.nikeshop.dataClass.DataProduct
import kotlinx.android.synthetic.main.fragment_shoppping_cart.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject


class ShoppingCartFragment : Fragment() {

    private lateinit var presenter: PresenterShoppingCartFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shoppping_cart, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView_shop_fragment.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val model = ModelShoppingCartFragment(activity?.applicationContext)

        presenter = PresenterShoppingCartFragment(this, model)
        presenter.onCreate()

        onClick()

    }

    fun onClick() {
        btn_pay_shop_fragment.setOnClickListener {
            context?.startActivity<ReceiverActivity>()
        }
    }

    fun showToast(text: String) {
        toast(text)
    }

    fun setUpRecycler(data: List<DataProduct>, presenter: PresenterShoppingCartFragment) {
        val adapter = RecyclerShopAdapter(activity?.applicationContext, presenter, data)
        recyclerView_shop_fragment.adapter = adapter
    }

    fun setPrices(total: String, final: String) {
        total_price_shopping_fragment.text = total
        final_price_shopping_fragment.text = final
        delivery_cost_shopping_fragment.text = "رایگان"
    }


    fun showEmptyCartImage() {
        img_empty_shopping_cart.visibility = View.VISIBLE
        txt_empty_shopping_cart.visibility = View.VISIBLE
        recyclerView_shop_fragment.visibility = View.GONE
        btn_pay_shop_fragment.visibility = View.GONE
        txt_buy_detail.visibility = View.GONE
        price_detail_layout.visibility = View.GONE
    }

    fun hideEmptyCartImage() {
        img_empty_shopping_cart.visibility = View.INVISIBLE
        txt_empty_shopping_cart.visibility = View.INVISIBLE
        recyclerView_shop_fragment.visibility = View.VISIBLE
        btn_pay_shop_fragment.visibility = View.VISIBLE
        txt_buy_detail.visibility = View.VISIBLE
        price_detail_layout.visibility = View.VISIBLE
    }

    fun showProgress() {
        progressBar_shop_fragment.visibility = View.VISIBLE
        recyclerView_shop_fragment.visibility = View.INVISIBLE
        img_empty_shopping_cart.visibility = View.INVISIBLE
        txt_empty_shopping_cart.visibility = View.INVISIBLE
        btn_pay_shop_fragment.visibility = View.INVISIBLE
        txt_buy_detail.visibility = View.INVISIBLE
        price_detail_layout.visibility = View.INVISIBLE
    }

    fun hideProgress() {
        progressBar_shop_fragment.visibility = View.INVISIBLE
        recyclerView_shop_fragment.visibility = View.VISIBLE
    }
}