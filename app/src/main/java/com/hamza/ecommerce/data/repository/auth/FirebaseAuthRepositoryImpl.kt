package com.hamza.ecommerce.data.repository.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.hamza.ecommerce.data.models.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FirebaseAuthRepositoryImpl(private val auth: FirebaseAuth = FirebaseAuth.getInstance()) :
    FirebaseAuthRepository {
    override fun loginWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())

            val result = auth.signInWithEmailAndPassword(email, password).await()
            auth.currentUser?.let { user ->
                (Resource.Success(user.uid))
            } ?: run {
                emit(Resource.Error(Exception("User not found")))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }

    }

    override fun loginWithGoogle(idToken: String): Flow<Resource<String>> = flow {
        try {
        } catch (e: Exception) {
            emit(Resource.Loading())
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = auth.signInWithCredential(credential).await()
            auth.currentUser?.let { user ->
                emit(Resource.Success(user.uid))

            } ?: run {
                emit(Resource.Error(Exception("User not found")))

            }
            emit(Resource.Error(e))
        }
    }

    override fun logout() {
        auth.signOut()
    }
}