package com.hamza.ecommerce.data.repository.user

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.hamza.ecommerce.data.datasource.datastore.DataStoreKeys.IS_USER_LOGGED_IN
import com.hamza.ecommerce.data.datasource.datastore.DataStoreKeys.USER_ID
import com.hamza.ecommerce.data.datasource.datastore.UserPreferencesDataSource
import com.hamza.ecommerce.data.datasource.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferenceRepositoryImpl(private val userPreferencesDataSource: UserPreferencesDataSource) :
    UserPreferenceRepository {
    override suspend fun isUserLoggedIn(): Flow<Boolean> =
        userPreferencesDataSource.isUserLoggedIn


    override suspend fun saveLoginState(isLoggedIn: Boolean) {
        userPreferencesDataSource.saveUserLoggedInState(isLoggedIn)
    }

    override suspend fun saveUserId(userId: String) {
        userPreferencesDataSource.saveUserId(userId)
    }

    override fun getUserId(): Flow<String?> =
        userPreferencesDataSource.userId


}