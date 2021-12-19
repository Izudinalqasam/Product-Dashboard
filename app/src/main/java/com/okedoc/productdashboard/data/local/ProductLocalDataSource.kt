package com.okedoc.productdashboard.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductLocalDataSource @Inject constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun saveToken(data: String) {
        dataStore.edit {
            it[PreferencesKeys.TOKEN] = data
        }
    }

    suspend fun getToken(): Flow<String> {
        return dataStore.data.map { it[PreferencesKeys.TOKEN] ?: "" }
    }

    suspend fun saveIsLogin(isLogin: Boolean) {
        dataStore.edit {
            it[PreferencesKeys.IS_LOGIN] = isLogin
        }
    }

    suspend fun isLogin(): Flow<Boolean> {
        return dataStore.data.map { it[PreferencesKeys.IS_LOGIN] ?: false }
    }
}