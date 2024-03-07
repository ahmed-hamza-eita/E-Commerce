package com.hamza.ecommerce.ui.common.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.hamza.ecommerce.data.repository.user.UserPreferenceRepositoryImpl
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class UserViewModel(private val userPreferencesRepository: UserPreferenceRepositoryImpl) :
    ViewModel() {

    suspend fun isUserLoggedIn() = userPreferencesRepository.isUserLoggedIn()
    suspend fun setLoggedInStatus(value: Boolean) {
        viewModelScope.launch(IO) {
            userPreferencesRepository.saveLoginStatus(value)
        }
    }
}
class UserViewModelFactory(private val userPreferencesRepository: UserPreferenceRepositoryImpl) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return UserViewModel(userPreferencesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}