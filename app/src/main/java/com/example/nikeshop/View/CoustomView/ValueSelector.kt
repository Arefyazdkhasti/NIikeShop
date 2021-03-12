package com.example.nikeshop.View.CoustomView

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.nikeshop.R
import kotlinx.android.synthetic.main.value_selector.view.*

class ValueSelector(
    contextInstance: Context,
    attrs: AttributeSet
) : FrameLayout(contextInstance,attrs) {

    val btnMinus:AppCompatImageView
    val btnPlus:AppCompatImageView
    private val valueNumber:AppCompatTextView

    private val minValue:Int
    private val maxValue:Int

    init {
       val mainView= inflate(context, R.layout.value_selector, this)

        btnMinus=mainView.btn_minus
        btnPlus=mainView.btn_plus
        valueNumber=mainView.value_number

        minValue= Int.MIN_VALUE
        maxValue=Int.MAX_VALUE

        //get attrs
        val typedArray = context.obtainStyledAttributes(attrs,R.styleable.ValueSelector)
        val number = typedArray.getString(R.styleable.ValueSelector_value_number)
        //recycle type array after use
        typedArray.recycle()

        setValue(Integer.valueOf(number.toString()))

        setClickHandler()
    }

    fun getValue():Int{
        if(valueNumber.text.toString().isEmpty()) {
            valueNumber.text = "0"
            return 0
        }

        return Integer.valueOf(valueNumber.text.toString())
    }

    fun setValue(newValue: Int){
        when {
            newValue > maxValue -> valueNumber.text = maxValue.toString()
            newValue < minValue -> valueNumber.text = minValue.toString()
            else -> valueNumber.text = newValue.toString()
        }
    }

    private fun setClickHandler(){
        btnPlus.setOnClickListener {
            incrementValue()
        }
        btnMinus.setOnClickListener {
            decrementValue()
        }
    }

    fun decrementValue() {
        val value = Integer.valueOf(valueNumber.text.toString())
        if (value -1 != 0) {
            setValue(value - 1)
        }
    }

    fun incrementValue() {
        val value = Integer.valueOf(valueNumber.text.toString())
        setValue(value + 1)
    }
}