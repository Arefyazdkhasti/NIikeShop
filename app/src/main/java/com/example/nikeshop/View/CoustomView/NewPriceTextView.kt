package com.example.nikeshop.View.CoustomView

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import com.example.nikeshop.R

class NewPriceTextView(context: Context, attributeSet: AttributeSet):FrameLayout(context,attributeSet) {

    var mainPrice="1500000"
    set(value){
        field=value
        setPrice()
        invalidate()
    }

    private val price:AppCompatTextView

    init {
        val view= inflate(context, R.layout.new_price_view,this)
        price=view.findViewById(R.id.price)
        setPrice()


    }

    private fun setPrice(){
        price.text=mainPrice
    }
}