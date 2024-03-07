package com.hamza.ecommerce.ui.auth.viewmodels

import androidx.lifecycle.ViewModel
import com.hamza.ecommerce.data.repository.user.UserPreferenceRepository
import com.hamza.ecommerce.data.repository.user.UserPreferenceRepositoryImpl

class LoginViewModel(private val repository: UserPreferenceRepositoryImpl) : ViewModel() {


}