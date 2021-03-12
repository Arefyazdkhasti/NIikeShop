package com.example.nikeshop.Presenter

import com.example.nikeshop.Model.ModelYourBoughtActivity
import com.example.nikeshop.View.ViewYourBoughtActivity
import com.example.nikeshop.`interface`.BaseLifeCycle
import com.example.nikeshop.dataClass.DataFormerBasket
import com.example.nikeshop.net.CountryPresenterListener

class PresenterYourBoughtActivity(
    private val view: ViewYourBoughtActivity,
    private val model: ModelYourBoughtActivity
):BaseLifeCycle {

    override fun onCreate() {
        onBackClick()
        setData()
    }

    private fun setData(){
        view.showProgress()
        model.getBasketIdData(object :CountryPresenterListener<List<DataFormerBasket>>{
            override fun onResponse(data: List<DataFormerBasket>) {
                view.setData(data)
                view.hideProgress()
            }

            override fun onFailure(error: String) {
                view.showToast(error)
                view.hideProgress()
            }

            override fun onEmptyResponse(isEmpty: Boolean) {
                if (isEmpty){
                    view.showNoBought()
                }else{
                    view.hideNoBought()
                }
                view.hideProgress()
            }

        })
    }

    private fun onBackClick(){
        view.onBackClick()
    }

    override fun onDestroy() {
    }
}