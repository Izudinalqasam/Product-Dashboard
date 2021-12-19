package com.okedoc.productdashboard.data.remote

import com.okedoc.productdashboard.data.model.product.ProductResponse
import retrofit2.http.*

interface ProductApiService {

    @GET("items")
    suspend fun getProducts(): List<ProductResponse>

    @FormUrlEncoded
    @POST("item/search")
    suspend fun getProductBySku(@Field("Sku") sku: String)

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(@FieldMap loginField: Map<String, String>)

    @FormUrlEncoded
    @POST("register")
    suspend fun register(@FieldMap registerField: Map<String, String>)

    @FormUrlEncoded
    @POST("item/add")
    suspend fun addProduct(@FieldMap product: Map<String, String>)

    @FormUrlEncoded
    @POST("item/update")
    suspend fun editProduct(@FieldMap product: Map<String, String>)

    @FormUrlEncoded
    @POST("")
    suspend fun deleteProduct(@Field("Sku") sku: String)
}