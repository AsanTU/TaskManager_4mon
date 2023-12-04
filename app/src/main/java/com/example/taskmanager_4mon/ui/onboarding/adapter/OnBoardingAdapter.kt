package com.example.taskmanager_4mon.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taskmanager_4mon.databinding.ItemOnboardingBinding
import com.example.taskmanager_4mon.model.OnBoarding

class OnBoardingAdapter(private val onClick: () -> Unit) :
    RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val list = arrayListOf<OnBoarding>(
        OnBoarding(
            "https://cdnl.iconscout.com/lottie/premium/preview-watermark/guy-working-from-home-10182046-8326718.mp4",
            "Introduction",
            "Welcome to the best task app."
        ),
        OnBoarding(
            "https://cdnl.iconscout.com/lottie/premium/preview-watermark/film-award-10170905-8304785.mp4",
            "Instructions",
            "Whatever comes to mind, write here."
        ),
        OnBoarding(
            "https://cdnl.iconscout.com/lottie/premium/preview-watermark/hand-gesture-4075138-3370597.mp4",
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

        fun bind(boarding: OnBoarding) = with(binding){
            with(boarding){
                tvTittle.text = boarding.title
                tvDescription.text = boarding.desc
                Glide.with(binding.ivBoard).load(image).into(binding.ivBoard)
                btnStart.isVisible = adapterPosition == list.lastIndex
                tvSkip.isVisible = adapterPosition != list.lastIndex
                tvSkip.setOnClickListener { onClick() }
                btnStart.setOnClickListener { onClick() }
            }
        }
    }
}