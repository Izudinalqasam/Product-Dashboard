package com.okedoc.productdashboard.domain.interactors

import com.okedoc.productdashboard.domain.repositories.ProductRepository
import javax.inject.Inject

class ProductInteractorImpl @Inject constructor(
    private val repository: ProductRepository
) : ProductInteractor {



}