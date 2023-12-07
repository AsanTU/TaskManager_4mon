package com.example.taskmanager_4mon.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.taskmanager_4mon.App
import com.example.taskmanager_4mon.R
import com.example.taskmanager_4mon.databinding.FragmentTaskBinding
import com.example.taskmanager_4mon.model.Task
import com.example.taskmanager_4mon.ui.home.HomeFragment

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setChanges()
    }

    private fun setChanges() {
        val task = arguments?.getSerializable(HomeFragment.TASK_EDIT_KEY) as Task?
        if (task != null) {
            binding.btnSave.text = getString(R.string._update)
            binding.etTitle.setText(task.title)
            binding.etDesc.setText(task.desc)
        }
        binding.btnSave.setOnClickListener {
            if (binding.etTitle.text.isNotEmpty()) {
                if (task != null) {
                    update(task)
                } else save()
                findNavController().navigateUp()
            } else binding.etTitle.error = "Error"
        }
    }

    private fun update(task: Task) {
        App.db.taskDao().update(
            task.copy(
                title = binding.etTitle.text.toString(),
                desc = binding.etDesc.text.toString(),
            )
        )
    }

    private fun save() {
        val data =
            Task(title = binding.etTitle.text.toString(), desc = binding.etDesc.text.toString())
        App.db.taskDao().insert(data)
    }

    private fun hideActionBar() {
        val actionBar: ActionBar? = (requireActivity() as? AppCompatActivity)?.supportActionBar
        actionBar?.hide()
    }

}