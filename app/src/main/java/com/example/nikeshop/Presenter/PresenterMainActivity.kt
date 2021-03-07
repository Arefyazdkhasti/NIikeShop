package com.example.nikeshop.Presenter

import android.util.Log
import com.example.nikeshop.Model.ModelMainActivity
import com.example.nikeshop.View.ViewMainActivity
import com.example.nikeshop.`interface`.BaseLifeCycle
import com.example.nikeshop.dataClass.DataResponse
import com.example.nikeshop.net.CountryPresenterListener


class PresenterMainActivity(
    private val view: ViewMainActivity,
    private val model: ModelMainActivity
) : BaseLifeCycle {


    override fun onCreate() {
        setUpView()
        setOnBottomNavigationClicked()
        onLogoClicked()
        setCartBadge()
    }


    private fun setUpView() {
        view.setUpView(model.getItemChecked(), model.getMainFragment())
    }

    fun badge(size: Int) {
        view.addBadge(size)
    }

    private fun setCartBadge() {
        model.getCartSize(object : CountryPresenterListener<DataResponse> {
            override fun onResponse(data: DataResponse) {
                view.addBadge(Integer.valueOf(data.error_msg))
                Log.i("TEST", data.error_msg)
                if (data.error_msg == "0") {
                    view.removeBadge()
                } else {
                    view.addBadge(Integer.valueOf(data.error_msg))
                }
            }

            override fun onFailure(error: String) {

            }

        })
    }

    private fun onLogoClicked() {
        view.onLogoClicked()
    }

    private fun setOnBottomNavigationClicked() {
        view.onBottomNavigationItemClickListener(model.getAllFragments())
    }

    override fun onDestroy() {

    }

}