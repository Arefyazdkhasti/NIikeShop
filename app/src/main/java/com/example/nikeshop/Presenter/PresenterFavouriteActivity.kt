package com.example.nikeshop.Presenter

import com.example.nikeshop.Model.ModelFavouriteActivity
import com.example.nikeshop.View.ViewFavouriteActivity
import com.example.nikeshop.`interface`.BaseLifeCycle
import com.example.nikeshop.dataClass.DataProduct
import com.example.nikeshop.net.CountryPresenterListener

class PresenterFavouriteActivity(
    private val view: ViewFavouriteActivity,
    private val model: ModelFavouriteActivity
) : BaseLifeCycle {
    override fun onCreate() {
        onClick()
        initRecycler()
    }

    private fun initRecycler() {
        view.showProgress()
        model.getFavouriteProducts(object : CountryPresenterListener<List<DataProduct>> {
            override fun onResponse(data: List<DataProduct>) {
                view.initRecycler(data)
                view.hideProgress()
            }

            override fun onFailure(error: String) {
                view.showToast(error)
                view.hideProgress()
            }

        })
    }

    private fun onClick() {
        view.onClickHandler()
    }

    override fun onDestroy() {}
}