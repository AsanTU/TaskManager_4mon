package com.example.taskmanager_4mon.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.taskmanager_4mon.App
import com.example.taskmanager_4mon.databinding.FragmentTaskBinding
import com.example.taskmanager_4mon.model.Task

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            dataTask()
        }
    }

    companion object {
        const val TASK_RESULT_KEY = "task,result.key"
        const val TASK_KEY = "task.key"
    }

    private fun isEditText(editText: EditText): Boolean {
        return editText.text.trim().isEmpty()
    }

    private fun dataTask() {
        val data = Task(
            title = binding.etTitle.text.toString(),
            desc = binding.etDesc.text.toString()
        )
        App.db.taskDao().insert(data)
        if (!isEditText(binding.etTitle)) {
            setFragmentResult(TASK_RESULT_KEY, bundleOf(TASK_KEY to data))
            findNavController().navigateUp()
        } else {
            Toast.makeText(requireContext(), "Введите текст", Toast.LENGTH_SHORT).show()
        }
    }
}