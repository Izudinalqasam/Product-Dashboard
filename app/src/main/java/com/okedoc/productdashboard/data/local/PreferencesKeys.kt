package com.okedoc.productdashboard.data.local

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    const val TOKEN_PREFS = "token_user"

    val IS_LOGIN = booleanPreferencesKey("login_user")
}