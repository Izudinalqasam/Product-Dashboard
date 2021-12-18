package com.okedoc.productdashboard.presentations.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.okedoc.productdashboard.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}