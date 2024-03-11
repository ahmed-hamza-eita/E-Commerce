package com.hamza.ecommerce.data.repository.user

import kotlinx.coroutines.flow.Flow

interface UserPreferenceRepository {
    suspend fun isUserLoggedIn(): Flow<Boolean>
    suspend fun saveLoginState(isLoggedIn: Boolean)
    suspend fun saveUserId(userId: String)
    fun getUserId(): Flow<String?>
}