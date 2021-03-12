package com.example.nikeshop.Presenter

import com.example.nikeshop.Model.ModelCommentActivity
import com.example.nikeshop.View.ViewCommentActivity
import com.example.nikeshop.`interface`.BaseLifeCycle

class PresenterCommentActivity(
    private val view:ViewCommentActivity,
    private val model:ModelCommentActivity
):BaseLifeCycle {

    override fun onCreate() {
        onClick()
        setUpTabLayout()
    }

    private fun onClick(){
        view.clickHandler()
    }
    private fun setUpTabLayout(){
        view.setUpTabLayout(
            model.getAdapter(),
            model.getObjectAccepted()
            //model.getObjectWaiting()
        )
    }
    override fun onDestroy() {

    }

}