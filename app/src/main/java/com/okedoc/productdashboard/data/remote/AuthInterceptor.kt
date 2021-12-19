package com.okedoc.productdashboard.data.remote

import android.content.SharedPreferences
import com.okedoc.productdashboard.data.local.PreferencesKeys
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sharedPreferences.getString(PreferencesKeys.TOKEN_PREFS, "")

        val newRequest = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        return chain.proceed(newRequest)
    }
}