package com.example.nikeshop.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nikeshop.Model.ModelArchiveActivity
import com.example.nikeshop.Presenter.PresenterArchiveActivity
import com.example.nikeshop.R
import com.example.nikeshop.View.ViewArchiveActivity
import com.example.nikeshop.`interface`.Utility
import org.koin.android.ext.android.inject

class ArchiveActivity : AppCompatActivity() , Utility{

    private lateinit var presenter: PresenterArchiveActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = ViewArchiveActivity(this, this)
        setContentView(view)

        val model = ModelArchiveActivity(this)
        presenter=PresenterArchiveActivity(view,model)
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