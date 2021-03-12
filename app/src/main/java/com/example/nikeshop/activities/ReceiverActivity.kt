package com.example.nikeshop.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nikeshop.Model.ModelReceiverActivity
import com.example.nikeshop.Presenter.PresenterReceiverActivity
import com.example.nikeshop.View.ViewReceiverActivity
import com.example.nikeshop.`interface`.Utility
import org.koin.android.ext.android.inject

class ReceiverActivity : AppCompatActivity(), Utility {

    private lateinit var presenter: PresenterReceiverActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view= ViewReceiverActivity(this,this)
        val model = ModelReceiverActivity(this)

        setContentView(view)

        presenter= PresenterReceiverActivity(view, model)
        presenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onFinished() {
        finish()
    }
}