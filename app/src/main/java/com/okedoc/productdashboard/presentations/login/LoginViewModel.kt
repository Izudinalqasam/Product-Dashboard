package com.okedoc.productdashboard.presentations.login

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
class LoginViewModel @Inject constructor(
    private val productInteractor: ProductInteractor
) : ViewModel() {

    private val _login = MutableLiveData<Resource<String>>()
    val login: LiveData<Resource<String>>
        get() = _login

    fun login(email: String, password: String) {
        _login.value = Resource.Loading()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = productInteractor.login(email, password)
                    _login.postValue(Resource.Success(successData = ""))
                    productInteractor.saveToken("")
                } catch (error: Exception) {
                    _login.postValue(Resource.Error(messageError = error.message ?: ""))
                }
            }
        }
    }
}