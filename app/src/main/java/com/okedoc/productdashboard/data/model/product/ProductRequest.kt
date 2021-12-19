package com.okedoc.productdashboard.data.model.product

data class ProductRequest(
    val sku: String,
    val name: String,
    val quantity: Int,
    val price: Int,
    val unit: String,
    val status: String
)