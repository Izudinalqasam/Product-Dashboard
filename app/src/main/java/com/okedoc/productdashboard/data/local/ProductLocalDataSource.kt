package com.okedoc.productdashboard.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductLocalDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val sharedPreferences: SharedPreferences
) {

    suspend fun saveToken(data: String) {
        sharedPreferences.edit {
            putString(PreferencesKeys.TOKEN_PREFS, data)
        }
    }

    suspend fun getToken(): String {
        return sharedPreferences.getString(PreferencesKeys.TOKEN_PREFS, "") ?: ""
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