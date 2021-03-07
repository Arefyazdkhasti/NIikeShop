package com.example.nikeshop.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nikeshop.Model.ModelQuestionActivity
import com.example.nikeshop.Presenter.PresenterQuestionActivity
import com.example.nikeshop.R
import com.example.nikeshop.View.ViewQuestionActivity
import com.example.nikeshop.`interface`.Utility
import org.koin.android.ext.android.inject

class QuestionActivity : AppCompatActivity(),Utility {

    private val model:ModelQuestionActivity by inject()
    private lateinit var presenter:PresenterQuestionActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view=ViewQuestionActivity(this,this)
        setContentView(view)

        presenter = PresenterQuestionActivity(view, model)
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