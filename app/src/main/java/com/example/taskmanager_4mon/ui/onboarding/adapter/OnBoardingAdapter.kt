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
            "https://cdn3d.iconscout.com/3d/free/thumb/free-mobile-in-hand-4391767-3640494.png",
            "Introduction",
            "Welcome to the best task app."
        ),
        OnBoarding(
            "https://3dicons.sgp1.cdn.digitaloceanspaces.com/v1/dynamic/gradient/copy-dynamic-gradient.png",
            "Instructions",
            "Whatever comes to mind, write here."
        ),
        OnBoarding(
            "https://static.vecteezy.com/system/resources/previews/016/716/172/original/like-3d-icon-png.png",
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