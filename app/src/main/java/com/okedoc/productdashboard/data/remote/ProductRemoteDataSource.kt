package com.okedoc.productdashboard.data.remote

import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(private val apiService: ProductApiService) {

    suspend fun getProducts() {
        return apiService.getProducts()
    }

    suspend fun getProductBySku(sku: String) {
        return apiService.getProductBySku(sku)
    }

    suspend fun login() {
        return apiService.login()
    }

    suspend fun register() {
        return apiService.register()
    }

    suspend fun addProduct() {
        return apiService.addProduct()
    }

    suspend fun editProduct() {
        return apiService.editProduct()
    }

    suspend fun deleteProduct() {
        return apiService.deleteProduct()
    }
}