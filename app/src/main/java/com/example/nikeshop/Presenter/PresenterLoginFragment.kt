package com.example.nikeshop.Presenter

import com.example.nikeshop.Model.ModelLoginFragment
import com.example.nikeshop.`interface`.BaseLifeCycle
import com.example.nikeshop.fragments.LoginFragment

class PresenterLoginFragment(
    private val view: LoginFragment,
    private val model: ModelLoginFragment
) :BaseLifeCycle{

    override fun onCreate() {
        onClick()
    }

    private fun onClick(){

    }

    override fun onDestroy() {}
}