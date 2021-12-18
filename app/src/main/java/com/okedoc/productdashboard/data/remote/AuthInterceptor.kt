package com.okedoc.productdashboard.data.remote

import com.okedoc.productdashboard.data.local.ProductLocalDataSource
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dataSource: ProductLocalDataSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder().addHeader("Authorization", "Bearer ")
            .build()

        return chain.proceed(newRequest)
    }
}