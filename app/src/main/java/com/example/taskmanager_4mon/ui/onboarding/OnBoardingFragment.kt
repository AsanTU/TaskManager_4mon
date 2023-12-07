package com.example.taskmanager_4mon.ui.onboarding

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskmanager_4mon.R
import com.example.taskmanager_4mon.data.local.Pref
import com.example.taskmanager_4mon.databinding.FragmentOnBoardingBinding
import com.example.taskmanager_4mon.ui.onboarding.adapter.OnBoardingAdapter

class OnBoardingFragment : Fragment() {

    private val adapter = OnBoardingAdapter(this::onClick)
    private lateinit var binding: FragmentOnBoardingBinding
    private val pref: Pref by lazy {
        Pref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initStatus()
        binding.viewpager.adapter = adapter
        binding.indicator.attachTo(binding.viewpager)
    }

    private fun onClick() {
        pref.onBoardingShow()
        findNavController().navigate(R.id.navigation_home)
    }

    private fun initStatus() {
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        activity?.window?.statusBarColor = Color.BLACK
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

}