package com.example.nikeshop.dataClass

data class DataComments(
    val id:Int,
    val title:String,
    val author:String,
    val created_at:String,
    val content:String,
    val accepted:Int
) {
}