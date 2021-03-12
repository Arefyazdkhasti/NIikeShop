package com.example.nikeshop.Presenter

import com.example.nikeshop.Model.ModelReceiverActivity
import com.example.nikeshop.View.ViewReceiverActivity
import com.example.nikeshop.`interface`.BaseLifeCycle
import com.example.nikeshop.dataClass.DataReceiver
import com.example.nikeshop.net.CountryPresenterListener

class PresenterReceiverActivity(
    private val view: ViewReceiverActivity,
    private val model: ModelReceiverActivity
) : BaseLifeCycle {
    override fun onCreate() {
        onBackClick()
        onClick()
        setPrice()
        setData()

    }

    private fun onBackClick() {
        view.onBackClickHandler()
    }

    private fun onClick() {

        view.onClickHandler(model.getEmail(), model.getApi())
        view.finishBasket(model.getEmail(), model.getApi(), model.getTotalPriceAsIntent())

    }

    private fun setData() {
        view.showSendDataProgress()
        model.getReceiverData(object : CountryPresenterListener<DataReceiver> {
            override fun onResponse(data: DataReceiver) {
                view.setDataInEdt(
                    data.name_and_family,
                    data.postal_code.toString(),
                    data.number.toString(),
                    data.address
                )
                view.editEnable()
                view.hideSendDataProgress()
            }

            override fun onFailure(error: String) {
                view.showToast(error)
                view.hideSendDataProgress()
            }

            override fun onEmptyResponse(isEmpty: Boolean) {
                super.onEmptyResponse(isEmpty)
                if (!isEmpty) {
                    view.editEnable()
                }
            }
        })
    }

    private fun setPrice() {
        view.setPrice(model.getTotalPriceAsIntent(), model.getFinalPriceAsIntent())
    }

    override fun onDestroy() {

    }

}