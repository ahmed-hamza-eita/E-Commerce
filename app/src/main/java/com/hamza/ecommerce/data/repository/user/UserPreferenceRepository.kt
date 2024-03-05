package com.hamza.ecommerce.data.repository.user

import kotlinx.coroutines.flow.Flow

interface UserPreferenceRepository {
    suspend fun isUserLoggedIn(): Flow<Boolean>
    suspend fun saveLoginStatus(isLoggedIn: Boolean)
    suspend fun saveUserId(userId: String)
}