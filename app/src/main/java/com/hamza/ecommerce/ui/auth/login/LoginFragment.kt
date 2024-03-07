package com.hamza.ecommerce.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hamza.ecommerce.R
import com.hamza.ecommerce.data.repository.user.UserPreferenceRepositoryImpl
import com.hamza.ecommerce.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
private val loginViewModel :LoginViewModel by lazy {
    LoginViewModel(UserPreferenceRepositoryImpl(requireActivity()))
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_login, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)


    }

    companion object {
        private const val TAG = "LoginFragment"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}