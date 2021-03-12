package com.example.nikeshop.View

import android.annotation.SuppressLint
import android.content.Context
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeshop.R
import com.example.nikeshop.`interface`.Utility
import com.example.nikeshop.adapter.RecyclerItemBoughtDetail
import com.example.nikeshop.dataClass.DataBoughtDetail
import kotlinx.android.synthetic.main.activity_bought_detail.view.*
import org.jetbrains.anko.toast

@SuppressLint("ViewConstructor")
class ViewBoughtDetailActivity(contextInstance: Context, private val utility: Utility) :
    FrameLayout(contextInstance) {
    
    private val imgBack:AppCompatImageView
    private val progress: ProgressBar
    private val recycler:RecyclerView

    init {
        val mainView= inflate(context, R.layout.activity_bought_detail,this)

        imgBack=mainView.image_back_buy_detail_activity
        progress=mainView.progress_bar_buy_detail_activity
        recycler=mainView.recycler_buy_detail_activity

        recycler.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
    }

    fun onBackClick(){
        imgBack.setOnClickListener {
            utility.onFinished()
        }
    }

    fun setUpRecycler(data:List<DataBoughtDetail>){
        val adapter= RecyclerItemBoughtDetail(context,data)
        recycler.adapter=adapter
    }

    fun showToast(text:String){
        context.toast(text)
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