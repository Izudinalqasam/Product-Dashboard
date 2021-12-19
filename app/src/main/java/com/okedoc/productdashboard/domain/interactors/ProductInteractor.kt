package com.okedoc.productdashboard.domain.interactors

import com.okedoc.productdashboard.data.model.product.ProductRequest
import com.okedoc.productdashboard.domain.entities.product.ProductDto

interface ProductInteractor {
    suspend fun getProducts(): List<ProductDto>

    suspend fun getProductBySku(sku: String)

    suspend fun login(email: String, password: String)

    suspend fun register(email: String, password: String)

    suspend fun addProduct(request: ProductRequest)

    suspend fun editProduct(request: ProductRequest)

    suspend fun deleteProduct(sku: String)

    suspend fun saveToken(data: String)

    suspend fun getToken()
}