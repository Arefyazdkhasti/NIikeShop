package com.example.nikeshop.dataClass

data class DataBoughtDetail(
    val product_id:Int,
    val title:String,
    val imgAddress:String,
    val price:String,
    val discount:Int,
    val priceDiscount:String,
    val count:Int,
    val user_email:String
)
