package com.okedoc.productdashboard.data.remote

import retrofit2.http.POST

interface ProductApiService {

    @POST("items")
    suspend fun getProducts()

    @POST("item/search")
    suspend fun getProductBySku(sku: String)

    @POST("auth/login")
    suspend fun login()

    @POST("register")
    suspend fun register()

    @POST("item/add")
    suspend fun addProduct()

    @POST("item/update")
    suspend fun editProduct()

    @POST("")
    suspend fun deleteProduct()
}