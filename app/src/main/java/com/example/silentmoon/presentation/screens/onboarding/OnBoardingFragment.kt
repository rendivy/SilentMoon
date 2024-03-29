package com.example.silentmoon.presentation.screens.onboarding

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.silentmoon.R
import com.example.silentmoon.databinding.OnBoardingFragmentBinding
import com.example.silentmoon.presentation.BottomBarVisibility
import com.example.silentmoon.presentation.screens.login.LoginFragment
import com.example.silentmoon.presentation.screens.registration.RegistrationFragment

class OnBoardingFragment : Fragment(R.layout.on_boarding_fragment) {

    private var _binding: OnBoardingFragmentBinding? = null

    private val binding get() = _binding!!


    override fun onStart() {
        super.onStart()
        (activity as? BottomBarVisibility)?.setBottomBarVisibility(false)
    }

    override fun onStop() {
        super.onStop()
        (activity as? BottomBarVisibility)?.setBottomBarVisibility(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = OnBoardingFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.singUpButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, RegistrationFragment())
                .addToBackStack(null)
                .commit()
        }

        val fullText = getString(R.string.already_have_an_account)
        val partToSpan = getString(R.string.sign_in)
        val spannableString = SpannableString(fullText)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view, LoginFragment())
                    .addToBackStack(null)
                    .commit()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        }

        val startIndexOfPart = fullText.indexOf(partToSpan)
        val endIndexOfPart = startIndexOfPart + partToSpan.length


        spannableString.setSpan(
            clickableSpan,
            startIndexOfPart,
            endIndexOfPart,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )


        val blueColor = ContextCompat.getColor(requireContext(), R.color.blue)


        spannableString.setSpan(
            ForegroundColorSpan(blueColor),
            startIndexOfPart,
            endIndexOfPart,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )


        binding.haveAnAccountLabel.text = spannableString
        binding.haveAnAccountLabel.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}