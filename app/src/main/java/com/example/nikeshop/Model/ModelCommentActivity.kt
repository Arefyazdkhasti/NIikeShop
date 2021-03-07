package com.example.nikeshop.Model

import androidx.appcompat.app.AppCompatActivity
import com.example.nikeshop.adapter.ViewPagerAdapter
import com.example.nikeshop.fragments.AcceptedCommentFragment
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class ModelCommentActivity(private val activity: AppCompatActivity) :KoinComponent{

    private val accepted:AcceptedCommentFragment by inject()

    fun getAdapter() = ViewPagerAdapter(activity.supportFragmentManager)

    fun getObjectAccepted() = accepted
}