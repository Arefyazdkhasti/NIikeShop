package com.example.nikeshop.View.CoustomView

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

//create custom text view with a line on text for discounted item

class CustomTextView(context: Context,attrs:AttributeSet):AppCompatTextView(context,attrs) {

    init {
        drawLineOnText()
        textSize = 14f
    }

    private fun drawLineOnText(){
        val span= SpannableString(text)
        span.setSpan(StrikethroughSpan(),0,text.length,Spanned.SPAN_MARK_MARK)
        text = span
    }

    fun setCustomSize(size:Float){
        textSize=size
        drawLineOnText()
    }
    fun setCustomText(value:String){
        text=value
        drawLineOnText()
    }

}