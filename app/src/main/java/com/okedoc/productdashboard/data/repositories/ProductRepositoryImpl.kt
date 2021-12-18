package com.okedoc.productdashboard.data.repositories

import com.okedoc.productdashboard.data.local.ProductLocalDataSource
import com.okedoc.productdashboard.data.remote.ProductRemoteDataSource
import com.okedoc.productdashboard.domain.repositories.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val localDataSource: ProductLocalDataSource,
    private val remoteDataSource: ProductRemoteDataSource,
): ProductRepository{

    override suspend fun getProducts() {
        return remoteDataSource.getProducts()
    }

    override suspend fun getProductBySku(sku: String) {
        return remoteDataSource.getProductBySku(sku)
    }

    override suspend fun login() {
        return remoteDataSource.login()
    }

    override suspend fun register() {
        return remoteDataSource.register()
    }

    override suspend fun addProduct() {
        return remoteDataSource.addProduct()
    }

    override suspend fun editProduct() {
        return remoteDataSource.editProduct()
    }

    override suspend fun deleteProduct() {
       return remoteDataSource.deleteProduct()
    }

    override suspend fun saveToken(data: String) {
        return localDataSource.saveToken(data)
    }

    override suspend fun getToken() {
        return localDataSource.saveToken("")
    }
}