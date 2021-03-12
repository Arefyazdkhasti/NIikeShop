package com.example.nikeshop.Presenter

import com.example.nikeshop.Model.ModelShoppingCartFragment
import com.example.nikeshop.`interface`.BaseLifeCycle
import com.example.nikeshop.dataClass.DataCart
import com.example.nikeshop.dataClass.DataProduct
import com.example.nikeshop.fragments.ShoppingCartFragment
import com.example.nikeshop.net.CountryPresenterListener

class PresenterShoppingCartFragment(
    private val view: ShoppingCartFragment,
    private val model: ModelShoppingCartFragment,
) : BaseLifeCycle {

    private var totalPrice = 0
    private var priceWithDiscount = 0

    override fun onCreate() {
        getDataRecycler()
    }

    override fun onRefresh() {
        getDataRecycler()
    }

    private fun getDataRecycler() {
        view.showProgress()

        model.getShopProduct(object : CountryPresenterListener<List<DataCart>> {
            override fun onResponse(data: List<DataCart>) {

                view.setUpRecycler(data, this@PresenterShoppingCartFragment)
                setTotalPrice(data)
                view.hideProgress()
                view.hideEmptyCartImage()
            }

            override fun onFailure(error: String) {
                //view.showToast(error)
                view.hideProgress()
                view.hideEmptyCartImage()
            }

            override fun onEmptyResponse(isEmpty: Boolean) {
                if (isEmpty) {
                    view.showEmptyCartImage()
                } else {
                    view.hideEmptyCartImage()
                }
            }

        })
    }


    private fun setTotalPrice(data: List<DataCart>) {

        data.forEach {

            val tp = Integer.valueOf(it.price) * it.count
            totalPrice += tp

            priceWithDiscount += if (it.discount == 1)
                Integer.valueOf(it.priceDiscount) * it.count
            else
                Integer.valueOf(it.price) * it.count

        }
        view.setPrices(totalPrice.toString(), priceWithDiscount.toString())
        view.onPayClick(totalPrice, priceWithDiscount)

    }

    override fun onDestroy() {}
}