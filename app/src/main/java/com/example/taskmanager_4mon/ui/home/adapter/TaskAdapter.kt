package com.example.taskmanager_4mon.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager_4mon.databinding.ItemTaskBinding
import com.example.taskmanager_4mon.model.Task

class TaskAdapter(
    val onClick: (task: Task) -> Boolean,
    val onUpdateClick: (task: Task) -> Unit
) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private val taskList = arrayListOf<Task>()

    fun addTasks(tasks: List<Task>) {
        taskList.clear()
        taskList.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = taskList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(taskList[position])
    }

    inner class ViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) = with(binding) {
            task.apply {
                tvTittle.text = title
                tvDescription.text = desc
            }
            itemView.setOnLongClickListener {
                onClick.invoke(task)
            }
            itemView.setOnClickListener {
                onUpdateClick(task)
            }
        }
    }

}