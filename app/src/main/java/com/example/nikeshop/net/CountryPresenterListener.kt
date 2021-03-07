package com.example.nikeshop.net

interface CountryPresenterListener<T> {

    fun onResponse(data:T)

    fun onFailure(error:String)

    fun onEmptyResponse(isEmpty: Boolean){}
}