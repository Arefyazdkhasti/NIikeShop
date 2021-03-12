package com.example.nikeshop.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.example.nikeshop.Model.ModelYourBoughtActivity
import com.example.nikeshop.Presenter.PresenterYourBoughtActivity
import com.example.nikeshop.R
import com.example.nikeshop.View.ViewYourBoughtActivity
import com.example.nikeshop.`interface`.Utility

class YourBoughtActivity : AppCompatActivity(), Utility {

    private lateinit var presenter: PresenterYourBoughtActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = ViewYourBoughtActivity(this, this)
        val model = ModelYourBoughtActivity(this)
        setContentView(view)

        presenter = PresenterYourBoughtActivity(view, model)
        presenter.onCreate()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onFinished() {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}