package com.example.nikeshop.View

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.nikeshop.Model.ModelMainActivity
import com.example.nikeshop.R
import com.example.nikeshop.`interface`.SetFragment
import com.example.nikeshop.`interface`.Utility
import com.example.nikeshop.activities.LoginActivity
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.notification_badge.view.*
import org.jetbrains.anko.startActivity


@SuppressLint("ViewConstructor")
class ViewMainActivity(
    contextInstant: Context,
    private val setFragment: SetFragment,
    private val utility: Utility
) : FrameLayout(contextInstant) {

    private val bottomNavigation: BottomNavigationView
    private val logo: AppCompatImageView


    init {
        val mainView = inflate(context, R.layout.activity_main, this)

        bottomNavigation = mainView.bottomNavigationView
        logo = mainView.logo

    }

    companion object{
        const val KEY_LOGOUT = "logout"
    }

    fun setUpView(itemChecked: Int, mainFragment: Fragment) {

        bottomNavigation.menu.getItem(itemChecked).isChecked = true
        addFragment(mainFragment)


        val pref = context.getSharedPreferences(
            LoginActivity.LOGIN_PREF,
            Context.MODE_PRIVATE
        )

        val name = pref.getString(LoginActivity.USER_NAME, "")
        val email = pref.getString(LoginActivity.USER_EMAIL, "")

        Log.i("User: ", "$name $email")

    }

    fun addBadge(number:Int) {
        val badge = bottomNavigation.getOrCreateBadge(R.id.menu_shopping_cart)
        badge.backgroundColor=ContextCompat.getColor(context,R.color.blue)
        badge.isVisible = true
        badge.number=number
    }

    fun removeBadge(){
        bottomNavigation.removeBadge(R.id.menu_shopping_cart)
    }

    /*fun refreshBadgeView() {
        val badgeIsVisible = notificationBadge.visibility != VISIBLE

        if (badgeIsVisible) {
            notificationBadge.visibility = VISIBLE
        } else {
            notificationBadge.visibility = GONE
        }
    }*/

    fun onBottomNavigationItemClickListener(fragments: Map<String, Fragment>) {
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    replaceFragment(fragments[ModelMainActivity.KEY_HOME_FRAGMENT] ?: Fragment())
                    true
                }
                R.id.menu_shopping_cart -> {
                    replaceFragment(
                        fragments[ModelMainActivity.KEY_SHOPPING_CART_FRAGMENT] ?: Fragment()
                    )
                    true
                }
                R.id.menu_account -> {
                    replaceFragment(fragments[ModelMainActivity.KEY_ACCOUNT_FRAGMENT] ?: Fragment())
                    true
                }
                else -> false
            }
        }
    }

    fun onLogoClicked() {
        logo.setOnClickListener {
            //context.startActivity<QuestionActivity>()
            //set login pref true
            val pref = context.getSharedPreferences(
                LoginActivity.LOGIN_PREF,
                Context.MODE_PRIVATE
            )
            val editor = pref.edit()
            editor.putBoolean(LoginActivity.IS_LOGGED_IN, false)
            editor.putString(LoginActivity.USER_NAME, "")
            editor.putString(LoginActivity.USER_EMAIL, "")
            editor.apply()

            //start mainActivity
            context.startActivity<LoginActivity>(KEY_LOGOUT to "logout")
            utility.onFinished()

        }
    }

    private fun addFragment(fragment: Fragment) {

        setFragment.addFragment(fragment)

    }

    private fun replaceFragment(fragment: Fragment) {

        setFragment.replaceFragment(fragment)

    }


}