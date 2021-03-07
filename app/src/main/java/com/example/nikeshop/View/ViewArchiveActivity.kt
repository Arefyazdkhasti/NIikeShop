package com.example.nikeshop.View

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeshop.R
import com.example.nikeshop.`interface`.Utility
import com.example.nikeshop.adapter.RecyclerItemArchiveAdapter
import com.example.nikeshop.dataClass.DataProduct
import kotlinx.android.synthetic.main.activity_archive.view.*
import org.jetbrains.anko.toast

@SuppressLint("ViewConstructor")
class ViewArchiveActivity(
    contextInstance: Context,
    private val utility: Utility
) : FrameLayout(contextInstance) {

    private val txtTitle: AppCompatTextView
    private val imgBack: AppCompatImageView
    private val recycler: RecyclerView

    init {
        val mainView = inflate(context, R.layout.activity_archive, this)

        txtTitle = mainView.title_Archive_activity
        imgBack = mainView.image_back_archive_activity
        recycler = mainView.recyclerView_archive_activity

        recycler.layoutManager = GridLayoutManager(context,2,LinearLayoutManager.VERTICAL,false)
    }


    fun onClickHandler() {
        imgBack.setOnClickListener {
            utility.onFinished()
        }
    }

    fun showToast(text:String){
        context.toast(text)
    }

    fun setDataInRecyclers(data:List<DataProduct>){
        recycler.adapter=RecyclerItemArchiveAdapter(context,data)
        recycler.visibility= View.VISIBLE
        progressBar_archive.visibility=View.INVISIBLE
    }


    fun setTitleText(title:String){
        txtTitle.text=title
    }
}