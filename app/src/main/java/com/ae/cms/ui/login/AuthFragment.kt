package com.ae.cms.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ae.cms.R
import com.ae.cms.databinding.FragmentLoginBinding

class AuthFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val sharedAuthViewModel: AuthViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (sharedAuthViewModel.authenticatedUser.value !== null) {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Data binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.apply {
            authViewModel = sharedAuthViewModel
            lifecycleOwner = viewLifecycleOwner

        }
        sharedAuthViewModel.authenticatedUser.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }
        return binding.root
    }

    override fun onResume() {
        Toast.makeText(
            context,
            sharedAuthViewModel.authenticatedUser.value?.email.toString(),
            Toast.LENGTH_LONG
        ).show()
        super.onResume()
    }
}