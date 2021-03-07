package com.example.nikeshop.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nikeshop.MainActivity
import com.example.nikeshop.Model.ModelLoginActivity
import com.example.nikeshop.Presenter.PresenterLoginActivity
import com.example.nikeshop.R
import com.example.nikeshop.View.ViewLoginActivity
import com.example.nikeshop.`interface`.Utility
import com.google.android.material.internal.NavigationMenuPresenter
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity(),Utility {

    private lateinit var presenter: PresenterLoginActivity

    companion object {
        const val LOGIN_PREF = "loginPref"
        const val IS_LOGGED_IN = "login"
        const val USER_NAME = "name"
        const val USER_EMAIL = "email"

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkLogin()

        val view = ViewLoginActivity(this,this)
        val model = ModelLoginActivity(this)
        setContentView(view)

        presenter = PresenterLoginActivity(view, model)
        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }


    override fun onRefreshed() {
        presenter.onCreate()
    }

    private fun checkLogin() {
        val pref = getSharedPreferences(LOGIN_PREF, Context.MODE_PRIVATE)

        if (pref.getBoolean(IS_LOGGED_IN, false))
            startActivity<MainActivity>()

    }
}