package com.okedoc.productdashboard.presentations.register

import androidx.lifecycle.ViewModel
import com.okedoc.productdashboard.domain.interactors.ProductInteractor
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class RegisterViewModel(private val productInteractor: ProductInteractor): ViewModel() {

}