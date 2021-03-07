package com.example.nikeshop.View.CoustomView

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import com.example.nikeshop.R

class PriceTextView(context: Context,attributeSet: AttributeSet):FrameLayout(context,attributeSet) {

    var mainPrice="1500000"
    set(value){
        field=value
        setPrice()
        invalidate()
    }

    private val price:AppCompatTextView

    init {
        val view= inflate(context, R.layout.price_view,this)
        price=view.findViewById(R.id.price)
        setPrice()

        /*val typedArray=context.obtainStyledAttributes(attributeSet,R.styleable.PriceTextView)
        val text=typedArray.getString(R.styleable.PriceTextView_mainPrice)
        typedArray.recycle()
        price.text=text*/

    }

    private fun setPrice(){
        price.text=mainPrice
    }
}