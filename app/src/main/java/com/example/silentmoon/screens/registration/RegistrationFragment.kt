package com.example.silentmoon.screens.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.silentmoon.R
import com.example.silentmoon.databinding.RegistartionFragmentBinding
import com.example.silentmoon.navigation.clearAllBackStack
import com.example.silentmoon.screens.welcome.WelcomeFragment

class RegistrationFragment : Fragment(R.layout.registartion_fragment) {


    private lateinit var binding: RegistartionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = RegistartionFragmentBinding.inflate(inflater, container, false)

        binding.singUpButton.setOnClickListener {
            parentFragmentManager.clearAllBackStack()
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.main_activity_coordinator_layout,
                    WelcomeFragment(binding.nameTextField.editText?.text.toString())
                )
                .commit()
        }

        return binding.root
    }
}