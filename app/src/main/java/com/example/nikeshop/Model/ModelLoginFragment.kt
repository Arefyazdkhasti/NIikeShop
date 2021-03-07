package com.example.nikeshop.Model

import com.example.nikeshop.net.ApiService
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class ModelLoginFragment :KoinComponent{

    private val apiService:ApiService by inject()

    fun getObjectApiService() = apiService
}