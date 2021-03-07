package com.example.nikeshop.Presenter

import com.example.nikeshop.Model.ModelReceiverActivity
import com.example.nikeshop.View.ViewReceiverActivity
import com.example.nikeshop.`interface`.BaseLifeCycle

class PresenterReceiverActivity(
    private val view:ViewReceiverActivity,
    private val model:ModelReceiverActivity
):BaseLifeCycle {
    override fun onCreate() {

    }

    private fun onClick(){
        view.onClickHandler()
    }
    override fun onDestroy() {

    }

}