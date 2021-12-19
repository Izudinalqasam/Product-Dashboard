package com.okedoc.productdashboard.domain.interactors

import com.okedoc.productdashboard.data.model.product.ProductRequest
import com.okedoc.productdashboard.domain.entities.product.ProductDto
import com.okedoc.productdashboard.domain.repositories.ProductRepository
import javax.inject.Inject

class ProductInteractorImpl @Inject constructor(
    private val repository: ProductRepository
) : ProductInteractor {

    override suspend fun getProducts(): List<ProductDto> {
        return repository.getProducts()
    }

    override suspend fun getProductBySku(sku: String) {
        return repository.getProductBySku(sku)
    }

    override suspend fun login(email: String, password: String) {
        return repository.login(email, password)
    }

    override suspend fun register(email: String, password: String) {
        return repository.register(email, password)
    }

    override suspend fun addProduct(request: ProductRequest) {
        return repository.addProduct(request)
    }

    override suspend fun editProduct(request: ProductRequest) {
        return repository.editProduct(request)
    }

    override suspend fun deleteProduct(sku: String) {
        return repository.deleteProduct(sku)
    }

    override suspend fun saveToken(data: String) {
        return repository.saveToken(data)
    }

    override suspend fun getToken(): String {
        return repository.getToken()
    }


}