package com.example.nikeshop.Model

import android.content.Context
import com.example.nikeshop.activities.LoginActivity

class ModelAccountFragment(private val context: Context? = null) {


    fun getName() = context?.getSharedPreferences(LoginActivity.LOGIN_PREF, Context.MODE_PRIVATE)
        ?.getString(LoginActivity.USER_NAME, "default name") ?: ""

    fun getEmail() = context?.getSharedPreferences(LoginActivity.LOGIN_PREF, Context.MODE_PRIVATE)
        ?.getString(LoginActivity.USER_EMAIL, "default Email") ?: ""
}