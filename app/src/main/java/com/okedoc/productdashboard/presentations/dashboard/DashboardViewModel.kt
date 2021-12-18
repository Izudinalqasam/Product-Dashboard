package com.okedoc.productdashboard.presentations.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okedoc.productdashboard.domain.interactors.ProductInteractor
import com.okedoc.productdashboard.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class DashboardViewModel(private val productInteractor: ProductInteractor) : ViewModel() {

    private val _products = MutableLiveData<Resource<String>>()
    val products: LiveData<Resource<String>>
        get() = _products
}