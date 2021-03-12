package com.example.nikeshop.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nikeshop.Model.ModelBoughtDetailActivity
import com.example.nikeshop.Presenter.PresenterBoughtDetailActivity
import com.example.nikeshop.View.ViewBoughtDetailActivity
import com.example.nikeshop.`interface`.Utility

class BoughtDetailActivity : AppCompatActivity(), Utility {

    private lateinit var presente: PresenterBoughtDetailActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = ViewBoughtDetailActivity(this, this)
        val model = ModelBoughtDetailActivity(this)
        setContentView(view)

        presente= PresenterBoughtDetailActivity(view, model)
        presente.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presente.onDestroy()
    }

    override fun onFinished() {
        finish()
    }
}