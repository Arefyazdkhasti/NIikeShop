package com.example.nikeshop.Presenter

import com.example.nikeshop.Model.ModelBoughtDetailActivity
import com.example.nikeshop.View.ViewBoughtDetailActivity
import com.example.nikeshop.`interface`.BaseLifeCycle
import com.example.nikeshop.dataClass.DataBoughtDetail
import com.example.nikeshop.net.CountryPresenterListener

class PresenterBoughtDetailActivity(
    private val view: ViewBoughtDetailActivity,
    private val model: ModelBoughtDetailActivity
) : BaseLifeCycle {

    override fun onCreate() {
        onBackClick()
        setUpRecycler()
    }

    private fun onBackClick() {
        view.onBackClick()
    }

    private fun setUpRecycler(){
        view.showProgress()
        model.getBoughtData(object :CountryPresenterListener<List<DataBoughtDetail>>{
            override fun onResponse(data: List<DataBoughtDetail>) {
                view.setUpRecycler(data)
                view.hideProgress()
            }

            override fun onFailure(error: String) {
                view.showToast(error)
                view.hideProgress()
            }

        })
    }
    override fun onDestroy() {}

}