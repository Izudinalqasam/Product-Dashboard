package com.okedoc.productdashboard.data.remote

import com.okedoc.productdashboard.data.model.product.ProductRequest
import com.okedoc.productdashboard.data.model.product.ProductResponse
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(private val apiService: ProductApiService) {

    suspend fun getProducts(): List<ProductResponse> {
        return apiService.getProducts()
    }

    suspend fun getProductBySku(sku: String) {
        return apiService.getProductBySku(sku)
    }

    suspend fun login(email: String, password: String) {
        return apiService.login(
            mapOf(
                "email" to email,
                "password" to password
            )
        )
    }

    suspend fun register(email: String, password: String) {
        return apiService.register(
            mapOf(
                "email" to email,
                "password" to password
            )
        )
    }

    suspend fun addProduct(request: ProductRequest) {
        return apiService.addProduct(
            mapOf(
                "sku" to request.sku,
                "product_name" to request.name,
                "qty" to request.quantity.toString(),
                "price" to request.price.toString(),
                "unit" to request.unit,
                "status" to request.status,
            )
        )
    }

    suspend fun editProduct(request: ProductRequest) {
        return apiService.editProduct(
            mapOf(
                "sku" to request.sku,
                "product_name" to request.name,
                "qty" to request.quantity.toString(),
                "price" to request.price.toString(),
                "unit" to request.unit,
                "status" to request.status,
            )
        )
    }

    suspend fun deleteProduct(sku: String) {
        return apiService.deleteProduct(sku)
    }
}