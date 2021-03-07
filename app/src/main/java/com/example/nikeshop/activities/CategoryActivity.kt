package com.example.nikeshop.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.example.nikeshop.Model.ModelCategoryActivity
import com.example.nikeshop.Presenter.PresenterCategoryActivity
import com.example.nikeshop.R
import com.example.nikeshop.View.ViewCategoryActivity
import com.example.nikeshop.`interface`.Utility
import org.koin.android.ext.android.inject


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