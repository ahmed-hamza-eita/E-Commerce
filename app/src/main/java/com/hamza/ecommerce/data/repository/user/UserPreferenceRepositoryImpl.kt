package com.hamza.ecommerce.data.repository.user

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.hamza.ecommerce.data.datasource.datastore.DataStoreKeys.IS_USER_LOGGED_IN
import com.hamza.ecommerce.data.datasource.datastore.DataStoreKeys.USER_ID
import com.hamza.ecommerce.data.datasource.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferenceRepositoryImpl(private val context: Context) : UserPreferenceRepository {
    override suspend fun isUserLoggedIn(): Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[IS_USER_LOGGED_IN] ?: false

        }

    override suspend fun saveLoginStatus(isLoggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_USER_LOGGED_IN] = isLoggedIn
        }
    }

    override suspend fun saveUserId(userId: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID] = userId
        }
    }
}