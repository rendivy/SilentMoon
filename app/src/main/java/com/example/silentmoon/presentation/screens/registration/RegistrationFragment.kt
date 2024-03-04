package com.example.silentmoon.presentation.screens.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.silentmoon.R
import com.example.silentmoon.databinding.RegistartionFragmentBinding
import com.example.silentmoon.presentation.BottomBarVisibility
import com.example.silentmoon.presentation.UserNameUpdateListener
import com.example.silentmoon.presentation.navigation.clearAllBackStack
import com.example.silentmoon.presentation.screens.welcome.WelcomeFragment

class RegistrationFragment : Fragment(R.layout.registartion_fragment) {


    private lateinit var binding: RegistartionFragmentBinding

    override fun onStart() {
        super.onStart()
        (activity as? BottomBarVisibility)?.setBottomBarVisibility(false)
    }

    override fun onStop() {
        super.onStop()
        (activity as? BottomBarVisibility)?.setBottomBarVisibility(true)
    }

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
                    R.id.fragment_container_view,
                    WelcomeFragment(binding.nameTextField.editText?.text.toString())
                )
                .commit()
            (activity as? UserNameUpdateListener)?.onUserNameUpdate(binding.nameEditText.text.toString())
        }

        return binding.root
    }
}