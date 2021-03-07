package com.example.nikeshop.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.nikeshop.Model.ModelCommentActivity
import com.example.nikeshop.Presenter.PresenterCommentActivity
import com.example.nikeshop.R
import com.example.nikeshop.View.ViewCommentActivity
import com.example.nikeshop.`interface`.Utility

class CommentActivity : AppCompatActivity(), Utility {

    private lateinit var presenter: PresenterCommentActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val model = ModelCommentActivity(this)
        val view = ViewCommentActivity(this, this)
        setContentView(view)

        presenter = PresenterCommentActivity(view, model)
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