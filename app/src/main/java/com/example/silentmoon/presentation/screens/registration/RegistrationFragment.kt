package com.example.silentmoon.presentation.screens.registration

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

        val fullText = getString(R.string.private_policy)
        val partToSpan = getString(R.string.spannable_policy)
        val spannableString = SpannableString(fullText)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(context, R.string.policy_toast, Toast.LENGTH_SHORT).show()
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