package com.example.nikeshop.dataClass

data class DataComments(
    val id:Int,
    val title:String,
    val author:String,
    val product_id:Int,
    val content:String,
    val created_at:String,
    val accepted:Int
) {
}