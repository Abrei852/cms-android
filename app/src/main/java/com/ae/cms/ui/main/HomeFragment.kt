package com.ae.cms.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ae.cms.R
import com.ae.cms.databinding.FragmentHomeBinding
import com.ae.cms.ui.login.AuthViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val sharedAuthViewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.apply {
            authViewModel = sharedAuthViewModel
            lifecycleOwner = viewLifecycleOwner

        }
        sharedAuthViewModel.authenticatedUser.observe(viewLifecycleOwner) {
            if (it == null) {
                findNavController().popBackStack(R.id.loginFragment, false)
            }
        }
        return binding.root
    }

}