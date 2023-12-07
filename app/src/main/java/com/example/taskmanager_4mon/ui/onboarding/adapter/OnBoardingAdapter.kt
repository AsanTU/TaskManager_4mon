package com.example.taskmanager_4mon.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taskmanager_4mon.R
import com.example.taskmanager_4mon.databinding.ItemOnboardingBinding
import com.example.taskmanager_4mon.model.OnBoarding

class OnBoardingAdapter(private val onClick: () -> Unit) :
    RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val list = arrayListOf<OnBoarding>(
        OnBoarding(
            R.raw.lottle1,
            "Introduction",
            "Welcome to the best task app."
        ),
        OnBoarding(
            R.raw.lottie,
            "Instructions",
            "Whatever comes to mind, write here."
        ),
        OnBoarding(
            R.raw.like,
            "Advantages",
            "You can record as many tasks as you like, whenever and wherever you want."
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(boarding: OnBoarding) = with(binding) {
            with(boarding) {
                tvTittle.text = boarding.title
                tvDescription.text = boarding.desc
                boarding.image?.let { ivBoard.setAnimation(it) }

                btnStart.isVisible = adapterPosition == list.lastIndex
                tvSkip.isVisible = adapterPosition != list.lastIndex
                tvSkip.setOnClickListener { onClick() }
                btnStart.setOnClickListener { onClick() }
            }
        }
    }
}