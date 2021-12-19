package com.okedoc.productdashboard.di

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.okedoc.productdashboard.BuildConfig
import com.okedoc.productdashboard.data.remote.AuthInterceptor
import com.okedoc.productdashboard.data.remote.ProductApiService
import com.okedoc.productdashboard.data.repositories.ProductRepositoryImpl
import com.okedoc.productdashboard.domain.interactors.ProductInteractor
import com.okedoc.productdashboard.domain.interactors.ProductInteractorImpl
import com.okedoc.productdashboard.domain.repositories.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore("ProductDashboard")

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return appContext.dataStore
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences {
        return appContext.getSharedPreferences("product-dashboard", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideProductApiService(retrofit: Retrofit): ProductApiService {
        return retrofit.create(ProductApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository =
        productRepositoryImpl

    @Provides
    @Singleton
    fun provideProductInteractor(productInteractorImpl: ProductInteractorImpl): ProductInteractor =
        productInteractorImpl
}