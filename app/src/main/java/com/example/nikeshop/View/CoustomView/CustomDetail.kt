package com.example.nikeshop.View.CoustomView

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.nikeshop.R
import kotlinx.android.synthetic.main.detail_view.view.*

class CustomDetail(
    contextInstant: Context,
    attrs: AttributeSet
) : FrameLayout(contextInstant, attrs) {

    private val icon:AppCompatImageView
    private val text:AppCompatTextView

    init{
        val mainView= inflate(context, R.layout.detail_view,this)

        icon=mainView.icon_detail_view
        text=mainView.text_detail_view

        //get attrs
        val typedArray = context.obtainStyledAttributes(attrs,R.styleable.CustomDetail)
        val iconDrawable = typedArray.getResourceId(R.styleable.CustomDetail_icon,1)
        val textTitle = typedArray.getString(R.styleable.CustomDetail_text)
        //recycle type array after use

        icon.setImageResource(iconDrawable)
        text.text=textTitle

        typedArray.recycle()
    }
}