package com.example.nikeshop

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.nikeshop.Model.ModelMainActivity
import com.example.nikeshop.Presenter.PresenterMainActivity
import com.example.nikeshop.View.ViewMainActivity
import com.example.nikeshop.`interface`.SetFragment
import com.example.nikeshop.`interface`.Utility
import com.example.nikeshop.activities.LoginActivity
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), SetFragment,Utility{

    private lateinit var presenter: PresenterMainActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = ViewMainActivity(this, this,this)
        val model = ModelMainActivity(this)
        setContentView(view)

        presenter = PresenterMainActivity(view, model)
        presenter.onCreate()

    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_frame, fragment)
            .commit()
    }

    override fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frame, fragment)
            .commit()
    }

    override fun onFinished() {
        finish()
    }

}