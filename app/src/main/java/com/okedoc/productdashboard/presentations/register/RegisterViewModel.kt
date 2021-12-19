package com.okedoc.productdashboard.presentations.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okedoc.productdashboard.domain.interactors.ProductInteractor
import com.okedoc.productdashboard.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val productInteractor: ProductInteractor
) : ViewModel() {

    private val _register = MutableLiveData<Resource<String>>()
    val register: LiveData<Resource<String>>
        get() = _register


    fun register(email: String, password: String) {
        _register.value = Resource.Loading()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = productInteractor.register(email, password)
                    _register.postValue(Resource.Success(successData = ""))
                } catch (error: Exception) {
                    _register.postValue(Resource.Error(messageError = error.message ?: ""))
                }
            }
        }
    }

}