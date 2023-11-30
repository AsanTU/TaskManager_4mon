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

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private var task:Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        task = arguments?.getSerializable("key") as Task?
        binding.etDesc.setText(task?.desc)
        binding.etTitle.setText(task?.title)

        if (task!=null){
            binding.btnSave.text = getString(R.string._update)
            binding.btnSave.setOnClickListener {
                val updateTask = task?.copy(
                    title = binding.etTitle.text.toString(),
                    desc = binding.etDesc.text.toString()
                )
                App.db.taskDao().update(updateTask!!)
                findNavController().navigateUp()
            }
        }else{
            binding.btnSave.setOnClickListener {
                dataTask()
                hideActionBar()
            }
        }
    }

    private fun isEditText(editText: EditText): Boolean {
        return editText.text.trim().isEmpty()
    }

    private fun dataTask() {
        if (!isEditText(binding.etTitle)) {
            save()
            findNavController().navigateUp()
        } else {
            Toast.makeText(requireContext(), "Введите текст", Toast.LENGTH_SHORT).show()
        }
    }

    private fun save() {
        val data = Task(title = binding.etTitle.text.toString(), desc = binding.etDesc.text.toString())
        App.db.taskDao().insert(data)
    }

    private fun hideActionBar() {
        val actionBar: ActionBar? = (requireActivity() as? AppCompatActivity)?.supportActionBar
        actionBar?.hide()
    }
}