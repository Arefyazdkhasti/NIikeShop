package com.example.nikeshop.View

import android.content.Context
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.nikeshop.R
import com.example.nikeshop.`interface`.Utility
import com.example.nikeshop.adapter.ViewPagerAdapter
import com.example.nikeshop.fragments.AcceptedCommentFragment
import com.example.nikeshop.fragments.LoginFragment
import com.example.nikeshop.fragments.RegisterFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_comment.view.*

class ViewCommentActivity(
    contextInstance: Context,
    private val utility: Utility
) : FrameLayout(contextInstance) {

    private val imgBack:AppCompatImageView
    private val tabLayout:TabLayout
    private val viewPager:ViewPager
    init {

        val mainView = inflate(context, R.layout.activity_comment, this)

        imgBack=mainView.image_back_comment_activity
        tabLayout=mainView.tabLayout_comment_activity
        viewPager=mainView.view_pager_comment_activity

    }

    fun clickHandler(){
        imgBack.setOnClickListener {
            utility.onFinished()
        }
    }

    fun setUpTabLayout(
        adapter: ViewPagerAdapter,
        accepted: AcceptedCommentFragment
        /*notAccepted: AcceptedCommentFragment*/
    ) {
        adapter.addFragment(accepted,"نظرات ثبت شده")
        adapter.addFragment(Fragment(),"نظرات در صف بررسی")

        viewPager.adapter=adapter
        tabLayout.setupWithViewPager(viewPager)

    }


}