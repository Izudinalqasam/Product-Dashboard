package com.okedoc.productdashboard.data.mapper

import com.okedoc.productdashboard.data.model.product.ProductResponse
import com.okedoc.productdashboard.domain.entities.product.ProductDto

object ProductMapper {

    fun mapProductResponseToDto(response: ProductResponse): ProductDto {
        return ProductDto(
            createdAt = response.createdAt ?: "",
            id = response.id ?: 0,
            image = response.image ?: "",
            price = response.price ?: 0,
            productName = response.productName ?: "",
            qty = response.qty ?: 0,
            sku = response.sku ?: "",
            status = response.status ?: 0,
            unit = response.unit ?: "",
            updatedAt = response.updatedAt ?: ""
        )
    }

    fun mapLoginResponseToDto() {

    }

    fun mapRegisterResponseToDto() {

    }
}