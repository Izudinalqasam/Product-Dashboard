package com.okedoc.productdashboard.presentations.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okedoc.productdashboard.data.model.product.ProductRequest
import com.okedoc.productdashboard.domain.entities.product.ProductDto
import com.okedoc.productdashboard.domain.interactors.ProductInteractor
import com.okedoc.productdashboard.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val productInteractor: ProductInteractor) :
    ViewModel() {

    private val _products = MutableLiveData<Resource<List<ProductDto>>>()
    val products: LiveData<Resource<List<ProductDto>>>
        get() = _products

    private val _productsDetail = MutableLiveData<Resource<String>>()
    val productDetail: LiveData<Resource<String>>
        get() = _productsDetail

    private val _addProduct = MutableLiveData<Resource<String>>()
    val addProduct: LiveData<Resource<String>>
        get() = _addProduct

    private val _deleteProduct = MutableLiveData<Resource<Boolean>>()
    val deleteProducts: LiveData<Resource<Boolean>>
        get() = _deleteProduct

    fun getAllProducts() {
        _products.value = Resource.Loading()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = productInteractor.getProducts()
                    _products.postValue(Resource.Success(successData = result))
                } catch (error: Exception) {
                    _products.postValue(Resource.Error(messageError = error.message ?: ""))
                }
            }
        }
    }

    fun getProductBySku(sku: String) {
        _products.value = Resource.Loading()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = productInteractor.getProductBySku(sku)
                    _products.postValue(Resource.Success(successData = listOf()))
                } catch (error: Exception) {
                    _products.postValue(Resource.Error(messageError = error.message ?: ""))
                }
            }
        }
    }

    fun addProduct(request: ProductRequest) {
        _addProduct.value = Resource.Loading()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = productInteractor.addProduct(request)
                    _addProduct.postValue(Resource.Success(successData = ""))
                } catch (error: Exception) {
                    _addProduct.postValue(Resource.Error(messageError = error.message ?: ""))
                }
            }
        }
    }

    fun updateProduct(request: ProductRequest) {
        _productsDetail.value = Resource.Loading()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = productInteractor.editProduct(request)
                    _productsDetail.postValue(Resource.Success(successData = ""))
                } catch (error: Exception) {
                    _productsDetail.postValue(Resource.Error(messageError = error.message ?: ""))
                }
            }
        }
    }

    fun deleteProduct(sku: String) {
        _deleteProduct.value = Resource.Loading()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = productInteractor.deleteProduct(sku)
                    _deleteProduct.postValue(Resource.Success(successData = false))
                } catch (error: Exception) {
                    _deleteProduct.postValue(Resource.Error(messageError = error.message ?: ""))

                }
            }
        }
    }
}