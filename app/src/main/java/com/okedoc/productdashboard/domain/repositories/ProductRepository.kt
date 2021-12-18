package com.okedoc.productdashboard.domain.repositories

interface ProductRepository {
    suspend fun getProducts()

    suspend fun getProductBySku(sku: String)

    suspend fun login()

    suspend fun register()

    suspend fun addProduct()

    suspend fun editProduct()

    suspend fun deleteProduct()

    suspend fun saveToken(data: String)

    suspend fun getToken()
}