package com.example.silentmoon.screens.login

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
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.silentmoon.R
import com.example.silentmoon.databinding.LoginFragmentBinding
import com.example.silentmoon.navigation.clearAllBackStack
import com.example.silentmoon.screens.registration.RegistrationFragment
import com.example.silentmoon.screens.welcome.WelcomeFragment

class LoginFragment : Fragment(R.layout.login_fragment) {

    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = LoginFragmentBinding.inflate(inflater, container, false)
        binding.forgotPasswordLabel.setOnClickListener {
            Toast.makeText(context, R.string.forgot_password_toast, Toast.LENGTH_SHORT).show()
        }
        binding.singUpButton.setOnClickListener {
            parentFragmentManager.clearAllBackStack()
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.main_activity_coordinator_layout,
                    WelcomeFragment(binding.emailTextField.editText?.text.toString()),
                    null
                )
                .commit()

        }


        val fullText = getString(R.string.dont_have_an_account)
        val partToSpan = "SIGN UP"
        val spannableString = SpannableString(fullText)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.main_activity_coordinator_layout, RegistrationFragment())
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

        return binding.root

    }


}