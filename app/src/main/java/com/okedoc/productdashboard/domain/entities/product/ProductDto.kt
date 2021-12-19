package com.okedoc.productdashboard.domain.entities.product

data class ProductDto(
    val createdAt: String = "",
    val id: Int = 0,
    val image: String = "",
    val price: Int = 0,
    val productName: String = "",
    val qty: Int = 0,
    val sku: String = "",
    val status: Int = 0,
    val unit: String = "",
    val updatedAt: String = ""
)