package com.example.nikeshop.activities

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import com.example.nikeshop.Model.ModelCategoryActivity
import com.example.nikeshop.Presenter.PresenterCategoryActivity
import com.example.nikeshop.R
import com.example.nikeshop.View.ViewCategoryActivity
import com.example.nikeshop.`interface`.Utility


class CategoryActivity : AppCompatActivity(),Utility {


    private lateinit var presenter: PresenterCategoryActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = ViewCategoryActivity(this,this)
        val model = ModelCategoryActivity(this)
        setContentView(view)

        presenter = PresenterCategoryActivity(view, model)

        presenter.onCreate()


    }

    override fun onFinished() {
        finish()
    }

    override fun onRefreshed() {
        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}