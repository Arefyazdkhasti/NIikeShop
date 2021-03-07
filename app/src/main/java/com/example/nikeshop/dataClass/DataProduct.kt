package com.example.nikeshop.dataClass

data class DataProduct(
    val id: Int,
    val title: String,
    val imgAddress: String,
    val price: String,
    val discount: Int,
    val priceDiscount: String,
    val rate:Float,
    val description:String
)
