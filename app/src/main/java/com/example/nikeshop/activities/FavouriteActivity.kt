package com.example.nikeshop.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.load.model.Model
import com.example.nikeshop.Model.ModelFavouriteActivity
import com.example.nikeshop.Presenter.PresenterFavouriteActivity
import com.example.nikeshop.R
import com.example.nikeshop.View.ViewFavouriteActivity
import com.example.nikeshop.`interface`.Utility
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

class FavouriteActivity : AppCompatActivity(), Utility {

    private lateinit var presenter: PresenterFavouriteActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = ViewFavouriteActivity(this, this)
        val model= ModelFavouriteActivity(this)
        setContentView(view)

        presenter = PresenterFavouriteActivity(view, model)
        presenter.onCreate()

    }


    override fun onFinished() {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
