package com.example.nikeshop.Presenter

import com.example.nikeshop.Model.ModelAccountFragment
import com.example.nikeshop.`interface`.BaseLifeCycle
import com.example.nikeshop.fragments.AccountFragment

class PresenterAccountFragment(
    private val view:AccountFragment,
    private val model:ModelAccountFragment
):BaseLifeCycle {

    override fun onCreate() {
        setDataUser()
    }

    private fun setDataUser(){
        view.setUserData(model.getName(),model.getEmail())
    }
    override fun onDestroy() {
    }
}