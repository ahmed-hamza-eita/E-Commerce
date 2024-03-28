package com.hamza.ecommerce.ui.auth.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.hamza.ecommerce.data.repository.auth.FirebaseAuthRepositoryImpl
import com.hamza.ecommerce.data.repository.user.UserPreferenceRepository
import com.hamza.ecommerce.data.repository.user.UserPreferenceRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow

class LoginViewModel(
    private val repository: UserPreferenceRepository,
    private val repo: FirebaseAuthRepositoryImpl
) : ViewModel() {

val email= MutableStateFlow("")
val password= MutableStateFlow("")


    companion object {
        const val TAG = "LoginViewModel"
    }
}


class LoginViewModelFactory(
    private val repository: UserPreferenceRepository,
    private val repo: FirebaseAuthRepositoryImpl
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return LoginViewModel(repository, repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
