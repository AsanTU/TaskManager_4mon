package com.example.taskmanager_4mon.ui.auth.phone

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.taskmanager_4mon.R
import com.example.taskmanager_4mon.databinding.FragmentPhoneBinding
import com.example.taskmanager_4mon.databinding.FragmentProfileBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class PhoneFragment : Fragment() {

    private lateinit var binding: FragmentPhoneBinding

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {}

        override fun onVerificationFailed(e: FirebaseException) {}

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken,
        ) {
            findNavController().navigate(R.id.acceptFragment, bundleOf(VER_KEY to verificationId))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideActionBar()
        giveMessage()
    }

    private fun giveMessage() {
        binding.btnSend.setOnClickListener {
            var number = "${binding.textNine.prefixText.toString()}${binding.etPhone.text.toString()}"
            val options = PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
                .setPhoneNumber(number)
                .setTimeout(60, TimeUnit.SECONDS)
                .setActivity(requireActivity())
                .setCallbacks(callbacks)
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        }


    }

    private fun hideActionBar() {
        val actionBar: ActionBar? = (requireActivity() as? AppCompatActivity)?.supportActionBar
        actionBar?.hide()
    }

    companion object {
        const val VER_KEY = "ver.id.key"
    }

}