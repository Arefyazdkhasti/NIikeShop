package com.example.nikeshop.View

import android.annotation.SuppressLint
import android.content.Context
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeshop.R
import com.example.nikeshop.`interface`.Utility
import com.example.nikeshop.adapter.RecyclerItemFormerBoughtAdapter
import com.example.nikeshop.dataClass.DataFormerBasket
import kotlinx.android.synthetic.main.activity_your_bought.view.*
import org.jetbrains.anko.toast

@SuppressLint("ViewConstructor")
class ViewYourBoughtActivity(contextInstance: Context, private val utility: Utility) :
    FrameLayout(contextInstance) {

    private val imgBack: AppCompatImageView
    private val recycler: RecyclerView

    private val progress:ProgressBar
    private val imgNo:AppCompatImageView
    private val txtNo:AppCompatTextView
    init {
        val mainView = inflate(context, R.layout.activity_your_bought, this)

        imgBack = mainView.image_back_your_bought_activity
        recycler = mainView.recycler_your_bought_activity

        progress=mainView.progress_bar_your_bought_activity
        imgNo=mainView.img_no_bought_your_bought_activity
        txtNo=mainView.txt_no_bought_your_bought_activity

        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    }

    fun onBackClick() {
        imgBack.setOnClickListener {
            utility.onFinished()
        }
    }

    fun setData(data: List<DataFormerBasket>) {

        val adapter = RecyclerItemFormerBoughtAdapter(context, data)
        recycler.adapter=adapter

    }

    fun showToast(text:String){
        context.toast(text)
    }

    fun showNoBought(){
        imgNo.visibility= VISIBLE
        txtNo.visibility= VISIBLE
    }

    fun hideNoBought(){
        imgNo.visibility= INVISIBLE
        txtNo.visibility= INVISIBLE
    }

    fun showProgress(){
        progress.visibility= VISIBLE
        recycler.visibility= INVISIBLE
    }

    fun hideProgress(){
        progress.visibility= INVISIBLE
        recycler.visibility= VISIBLE
    }
}