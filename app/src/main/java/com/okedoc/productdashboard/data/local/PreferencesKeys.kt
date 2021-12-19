package com.okedoc.productdashboard.data.local

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val TOKEN = stringPreferencesKey("token_user")
    val IS_LOGIN = booleanPreferencesKey("login_user")
}