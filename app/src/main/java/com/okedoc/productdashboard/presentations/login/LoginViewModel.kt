package com.okedoc.productdashboard.presentations.login

import androidx.lifecycle.ViewModel
import com.okedoc.productdashboard.domain.interactors.ProductInteractor
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class LoginViewModel(private val productInteractor: ProductInteractor): ViewModel() {

}