package com.hp.mystore.model

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val rating: Float,
    val brand: String,
    val images: List<String>
)
