package com.hamza.ecommerce.data.datasource.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferencesDataSource(private val context: Context) {
    //private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

    suspend fun saveUserLoggedInState(isUserLoggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.IS_USER_LOGGED_IN] = isUserLoggedIn
        }
    }

    suspend fun saveUserId(userId: String) {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.USER_ID] = userId
        }
    }

    val isUserLoggedIn: Flow<Boolean> = context.dataStore.data.map { it ->
        it[DataStoreKeys.IS_USER_LOGGED_IN] ?: false
    }
    val userId: Flow<String?> = context.dataStore.data.map { it ->
        it[DataStoreKeys.USER_ID]
    }


}