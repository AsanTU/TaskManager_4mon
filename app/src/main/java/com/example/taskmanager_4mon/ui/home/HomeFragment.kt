package com.example.taskmanager_4mon.ui.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.taskmanager_4mon.App
import com.example.taskmanager_4mon.R
import com.example.taskmanager_4mon.databinding.FragmentHomeBinding
import com.example.taskmanager_4mon.model.Task
import com.example.taskmanager_4mon.ui.alertdialog.AlertDialogFragment
import com.example.taskmanager_4mon.ui.home.adapter.TaskAdapter
import com.example.taskmanager_4mon.ui.task.TaskFragment.Companion.TASK_KEY
import com.example.taskmanager_4mon.ui.task.TaskFragment.Companion.TASK_RESULT_KEY

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val adapter = TaskAdapter(this::onLongClick)

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun onLongClick(position:Task):Boolean{
        val alertDialogBuilder= AlertDialog.Builder(requireContext())
            .setTitle("Удаление item")
            .setMessage("Удалить текст?")

            .setPositiveButton("Подтвердить"){ _, _, ->
                App.db.taskDao().delete(position)
                findNavController().navigate(R.id.navigation_home)
            }
            .setNegativeButton("Отмена"){_, _, ->
                findNavController().navigateUp()
            }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
        return true
    }

    private fun addTasks() {
        binding.rvTasks.adapter = adapter
        val data = App.db.taskDao().getAll()
        adapter.addTasks(data)
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}