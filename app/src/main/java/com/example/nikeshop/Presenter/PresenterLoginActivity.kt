package com.example.nikeshop.Presenter

import com.example.nikeshop.Model.ModelLoginActivity
import com.example.nikeshop.View.ViewLoginActivity
import com.example.nikeshop.`interface`.BaseLifeCycle

class PresenterLoginActivity(
    private val view: ViewLoginActivity,
    private val model: ModelLoginActivity
) : BaseLifeCycle {

    override fun onCreate() {
        checkNet()
        setUpTabLayout()
    }

    private fun checkNet() {
        view.checkInternet(model.checkNetWork())
    }

    private fun setUpTabLayout(){
        view.setUpTabLayout(
            model.getAdapter(),
            model.getObjectLogin(),
            model.getObjectRegister()
        )

    }

    override fun onDestroy() {

    }
}