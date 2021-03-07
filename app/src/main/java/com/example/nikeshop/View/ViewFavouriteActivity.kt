package com.example.nikeshop.View

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeshop.R
import com.example.nikeshop.`interface`.Utility
import com.example.nikeshop.adapter.RecyclerItemProductAdapter
import com.example.nikeshop.dataClass.DataProduct
import kotlinx.android.synthetic.main.activity_favourite.view.*
import org.jetbrains.anko.toast

@SuppressLint("ViewConstructor")
class ViewFavouriteActivity(
    contextInstance: Context,
    private val utility: Utility
) : FrameLayout(contextInstance) {

    private val imgBack:AppCompatImageView
    private val progress:ProgressBar
    private val recycler:RecyclerView

    init {
        val mainView= inflate(context, R.layout.activity_favourite,this)

        imgBack=mainView.image_back_favourite_activity
        progress=mainView.progressBar_favourite_activity
        recycler=mainView.recyclerView_favourite_activity
    }

    fun initRecycler(data:List<DataProduct>){
        recycler.layoutManager=GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
        val adapter=RecyclerItemProductAdapter(context,data)
        recycler.adapter = adapter
    }

    fun showToast(text:String){
        context.toast(text)
    }

    fun showProgress(){
        progress.visibility=VISIBLE
        recycler.visibility= INVISIBLE
    }

    fun hideProgress(){
        progress.visibility=INVISIBLE
        recycler.visibility= VISIBLE
    }

    fun onClickHandler(){
        imgBack.setOnClickListener {
            utility.onFinished()
        }
    }
}