package com.example.taskmanager_4mon.ui.home

import android.os.Bundle
import android.support.v4.os.IResultReceiver.Default
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.example.taskmanager_4mon.App
import com.example.taskmanager_4mon.R
import com.example.taskmanager_4mon.databinding.FragmentHomeBinding
import com.example.taskmanager_4mon.model.Task
import com.example.taskmanager_4mon.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val adapter = TaskAdapter(this::onLongClick, this::onUpdateClick)

    private val binding get() = _binding!!
    private val task = Task()

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
        addTasks()
        binding.rvTasks.adapter = adapter
        hideActionBar()
        navigateTask()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            requireActivity().finish()
        }
    }

    private fun navigateTask() {
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
        addTasks()
    }

    private fun onLongClick(task: Task): Boolean {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.delete_item))
            .setMessage(getString(R.string.delete_text))
            .setPositiveButton(getString(R.string.delete_accept)) { _, _ ->
                App.db.taskDao().delete(task)
                addTasks()
            }
            .setNegativeButton(getString(R.string.cancel)) { _, _ -> }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
        return true
    }

    private fun onUpdateClick(task: Task) {
        findNavController().navigate(R.id.taskFragment, bundleOf(TASK_EDIT_KEY to task))
    }

    private fun addTasks() {
        val data = App.db.taskDao().getAll()
        adapter.addTasks(data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun hideActionBar() {
        val actionBar: ActionBar? = (requireActivity() as? AppCompatActivity)?.supportActionBar
        actionBar?.hide()
    }

    companion object {
        const val TASK_EDIT_KEY = "task.edit.key"
    }

}