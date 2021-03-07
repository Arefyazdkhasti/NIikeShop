package com.example.nikeshop.Model

import androidx.appcompat.app.AppCompatActivity
import com.example.nikeshop.View.ViewMainActivity
import com.example.nikeshop.adapter.ViewPagerAdapter
import com.example.nikeshop.fragments.LoginFragment
import com.example.nikeshop.fragments.RegisterFragment
import com.example.nikeshop.utitlity.NetWorkChecker
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


class ModelLoginActivity(private val activity: AppCompatActivity) :KoinComponent{

    private val login:LoginFragment by inject()
    private val register:RegisterFragment by inject()

    fun getReloadIntent(): String = activity.intent.getStringExtra(ViewMainActivity.KEY_LOGOUT) ?: " "

    fun checkNetWork() = NetWorkChecker(activity).networkStatus()

    fun getObjectLogin() = login

    fun getObjectRegister() = register

    fun getAdapter() = ViewPagerAdapter(activity.supportFragmentManager)
}