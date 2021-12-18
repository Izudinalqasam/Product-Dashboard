package com.okedoc.productdashboard.di

import com.okedoc.productdashboard.domain.interactors.ProductInteractor
import com.okedoc.productdashboard.domain.interactors.ProductInteractorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class DashboardModule {

    @Provides
    @ActivityScoped
    fun provideProductInteractor(productInteractorImpl: ProductInteractorImpl): ProductInteractor =
        productInteractorImpl
}