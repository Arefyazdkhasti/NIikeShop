package com.example.nikeshop.Model

import androidx.appcompat.app.AppCompatActivity
import com.example.nikeshop.adapter.ViewPagerAdapter
import com.example.nikeshop.fragments.AcceptedCommentFragment
import com.example.nikeshop.fragments.WaitingCommentFragment
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import androidx.fragment.app.Fragment

class ModelCommentActivity(private val activity: AppCompatActivity) : KoinComponent {

    private val accepted: AcceptedCommentFragment by inject()
   // private val waiting: WaitingCommentFragment by inject()

    fun getAdapter() = ViewPagerAdapter(activity.supportFragmentManager)

    fun getObjectAccepted() = accepted

   // fun getObjectWaiting() = waiting
}