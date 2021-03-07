package com.example.nikeshop.Presenter

import com.example.nikeshop.Model.ModelShoppingCartFragment
import com.example.nikeshop.`interface`.BaseLifeCycle
import com.example.nikeshop.dataClass.DataProduct
import com.example.nikeshop.fragments.ShoppingCartFragment
import com.example.nikeshop.net.CountryPresenterListener

class PresenterShoppingCartFragment(
    private val view: ShoppingCartFragment,
    private val model: ModelShoppingCartFragment,
) : BaseLifeCycle {

    override fun onCreate() {
        getDataRecycler()
    }

    override fun onRefresh() {
        getDataRecycler()
    }
    private fun getDataRecycler() {
        view.showProgress()

        model.getShopProduct(object : CountryPresenterListener<List<DataProduct>> {
            override fun onResponse(data: List<DataProduct>) {

                view.setUpRecycler(data,this@PresenterShoppingCartFragment)
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

    private fun setTotalPrice(data: List<DataProduct>) {
        var totalPrice = 0
        var priceWithDiscount = 0

        data.forEach {

            totalPrice += Integer.valueOf(it.price)

            priceWithDiscount += if (it.discount == 1)
                Integer.valueOf(it.priceDiscount)
            else
                Integer.valueOf(it.price)

            view.setPrices(totalPrice.toString(), priceWithDiscount.toString())

        }
    }

    override fun onDestroy() {}
}