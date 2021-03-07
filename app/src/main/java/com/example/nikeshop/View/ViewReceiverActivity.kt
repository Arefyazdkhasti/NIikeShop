package com.example.nikeshop.View

import android.annotation.SuppressLint
import android.content.Context
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import com.example.nikeshop.R
import com.example.nikeshop.`interface`.Utility
import kotlinx.android.synthetic.main.activity_reciever.view.*

@SuppressLint("ViewConstructor")
class ViewReceiverActivity(contextInstance: Context, private val utility: Utility):FrameLayout(contextInstance) {

    private val imgBack:AppCompatImageView

    init {
        val mainView= inflate(context, R.layout.activity_reciever,this)

        imgBack=mainView.image_back_receiver_activity
    }

    fun onClickHandler(){
        imgBack.setOnClickListener {
            utility.onFinished()
        }
    }
}