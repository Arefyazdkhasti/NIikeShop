package com.example.nikeshop.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nikeshop.Model.ModelDetailActivity
import com.example.nikeshop.Presenter.PresenterDetailActivity
import com.example.nikeshop.R
import com.example.nikeshop.View.ViewDetailActivity
import com.example.nikeshop.`interface`.Utility

class DetailActivity : AppCompatActivity(),Utility {

    private lateinit var presenter:PresenterDetailActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view=ViewDetailActivity(this,this)
        val model=ModelDetailActivity(this)

        setContentView(view)

        presenter= PresenterDetailActivity(view, model)
        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onFinished() {
        finish()
    }

    override fun onRefreshed() {
        presenter.onRefresh()
    }
}