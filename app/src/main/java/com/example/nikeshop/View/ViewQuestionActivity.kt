package com.example.nikeshop.View

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeshop.R
import com.example.nikeshop.`interface`.Utility
import com.example.nikeshop.adapter.RecyclerQuestionAdapter
import com.example.nikeshop.dataClass.DataQuestion
import kotlinx.android.synthetic.main.activity_question.view.*
import org.jetbrains.anko.toast

@SuppressLint("ViewConstructor")
class ViewQuestionActivity(contextInstance: Context, private val utility: Utility) :
    FrameLayout(contextInstance) {

    private val imgBack: AppCompatImageView
    private val recycler: RecyclerView
    private val progress: ProgressBar

    init {
        val mainView = inflate(context, R.layout.activity_question, this)

        imgBack = mainView.image_back_question_activity
        progress = mainView.progress_activity_question
        recycler = mainView.recycler_activity_question
    }

    fun onClickHandler() {
        imgBack.setOnClickListener {
            utility.onFinished()
        }
    }

    fun initRecycler(data: List<DataQuestion>) {
        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = RecyclerQuestionAdapter(data)
        showProgress()
    }

    fun showToast(text: String) {
        context.toast(text)
    }

    fun showProgress() {
        progress.visibility = View.VISIBLE
        recycler.visibility = View.INVISIBLE
    }

    fun hideProgress() {
        recycler.visibility = View.VISIBLE
        progress.visibility = View.INVISIBLE
    }
}