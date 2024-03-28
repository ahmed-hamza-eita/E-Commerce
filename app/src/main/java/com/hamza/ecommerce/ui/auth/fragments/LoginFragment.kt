package com.hamza.ecommerce.ui.auth.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hamza.ecommerce.R
import com.hamza.ecommerce.data.datasource.datastore.UserPreferencesDataSource
import com.hamza.ecommerce.data.repository.auth.FirebaseAuthRepositoryImpl
import com.hamza.ecommerce.data.repository.user.UserPreferenceRepositoryImpl
import com.hamza.ecommerce.databinding.FragmentLoginBinding
import com.hamza.ecommerce.ui.auth.viewmodels.LoginViewModel
import com.hamza.ecommerce.ui.auth.viewmodels.LoginViewModelFactory


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(
            UserPreferenceRepositoryImpl(UserPreferencesDataSource(requireActivity())),
            repo = FirebaseAuthRepositoryImpl()
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = loginViewModel
        }
        return binding.root

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "LoginFragment"
    }
}