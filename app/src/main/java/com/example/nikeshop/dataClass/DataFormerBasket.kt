package com.example.nikeshop.dataClass

data class DataFormerBasket(
    val error:Boolean,
    val basket_id:String,
    val created_at:String,
    val imgAddress:List<DataImage>
)
