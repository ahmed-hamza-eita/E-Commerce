package com.hamza.ecommerce.data.repository.auth

import com.hamza.ecommerce.data.models.Resource
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthRepository {
    fun loginWithEmailAndPassword(email: String, password: String): Flow<Resource<String>>
    fun loginWithGoogle(idToken: String): Flow<Resource<String>>

}