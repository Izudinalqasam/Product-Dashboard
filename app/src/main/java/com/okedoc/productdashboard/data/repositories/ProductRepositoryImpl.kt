package com.okedoc.productdashboard.data.repositories

import com.okedoc.productdashboard.data.local.ProductLocalDataSource
import com.okedoc.productdashboard.data.mapper.ProductMapper
import com.okedoc.productdashboard.data.model.product.ProductRequest
import com.okedoc.productdashboard.data.remote.ProductRemoteDataSource
import com.okedoc.productdashboard.domain.entities.product.ProductDto
import com.okedoc.productdashboard.domain.repositories.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val localDataSource: ProductLocalDataSource,
    private val remoteDataSource: ProductRemoteDataSource,
) : ProductRepository {

    override suspend fun getProducts(): List<ProductDto> {
        return remoteDataSource.getProducts().map { ProductMapper.mapProductResponseToDto(it) }
    }

    override suspend fun getProductBySku(sku: String) {
        return remoteDataSource.getProductBySku(sku)
    }

    override suspend fun login(email: String, password: String) {
        return remoteDataSource.login(email, password)
    }

    override suspend fun register(email: String, password: String) {
        return remoteDataSource.register(email, password)
    }

    override suspend fun addProduct(request: ProductRequest) {
        return remoteDataSource.addProduct(request)
    }

    override suspend fun editProduct(request: ProductRequest) {
        return remoteDataSource.editProduct(request)
    }

    override suspend fun deleteProduct(sku: String) {
        return remoteDataSource.deleteProduct(sku)
    }

    override suspend fun saveToken(data: String) {
        return localDataSource.saveToken(data)
    }

    override suspend fun getToken(): String {
        return localDataSource.getToken()
    }
}