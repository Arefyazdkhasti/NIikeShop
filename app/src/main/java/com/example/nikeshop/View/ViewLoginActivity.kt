package com.example.nikeshop.View

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.ViewPager
import com.example.nikeshop.R
import com.example.nikeshop.`interface`.Utility
import com.example.nikeshop.adapter.ViewPagerAdapter
import com.example.nikeshop.fragments.LoginFragment
import com.example.nikeshop.fragments.RegisterFragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_login.view.*

@SuppressLint("ViewConstructor")
class ViewLoginActivity(contextInstance: Context, private val utility: Utility) :
    FrameLayout(contextInstance) {

    private val tabLayout: TabLayout
    private val viewPager: ViewPager
    private val root: ConstraintLayout

    init {
        val mainView = inflate(context, R.layout.activity_login, this)

        tabLayout = mainView.findViewById(R.id.tab_Layout_login_activity)
        viewPager = mainView.viewPager_login_activity
        root = mainView.root_login_activity
    }

    fun onClickHandler() {

    }

    fun checkInternet(netInfo: Boolean) {
        if (netInfo) {

            tabLayout.visibility = VISIBLE
            viewPager.visibility = VISIBLE

        } else showSnackBar()
    }

    private fun showSnackBar() {

        val snack = Snackbar.make(
            root,
            "خطای اتصال به اینترنت",
            Snackbar.LENGTH_INDEFINITE
        )
        snack.setActionTextColor(Color.RED)
        snack.setAction("تلاش مجدد") {
            snack.dismiss()
            utility.onRefreshed()
        }
        snack.show()
    }

    fun setUpTabLayout(
        adapter: ViewPagerAdapter,
        login: LoginFragment,
        register: RegisterFragment
    ) {
        adapter.addFragment(login, "ورود")
        adapter.addFragment(register, "ثبت نام")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }


}