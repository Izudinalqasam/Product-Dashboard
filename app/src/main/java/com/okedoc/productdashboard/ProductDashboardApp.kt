package com.okedoc.productdashboard

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ProductDashboardApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}