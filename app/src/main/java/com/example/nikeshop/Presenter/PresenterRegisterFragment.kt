package com.example.nikeshop.Presenter

import com.example.nikeshop.Model.ModelRegisterFragment
import com.example.nikeshop.`interface`.BaseLifeCycle
import com.example.nikeshop.fragments.RegisterFragment

class PresenterRegisterFragment(
    private val view: RegisterFragment,
    private val model: ModelRegisterFragment
) :BaseLifeCycle{

    override fun onCreate() {
        onClick()
    }

    private fun onClick(){

    }

    override fun onDestroy() {}
}