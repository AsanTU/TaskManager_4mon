package com.example.taskmanager_4mon.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.taskmanager_4mon.R
import com.example.taskmanager_4mon.databinding.FragmentDashboardBinding
import com.example.taskmanager_4mon.model.Book
import com.example.taskmanager_4mon.utils.showToast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        hideActionBar()
        saveData()
    }

    private fun saveData() {
        binding.btnSave.setOnClickListener {
            val data = Book(
                name = binding.etName.text.toString(),
                author = binding.etAuthor.text.toString(),
            )
            db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString())
                .add(data)
                .addOnSuccessListener {
                    showToast(getString(R.string.success_saved))
                }
                .addOnFailureListener {
                    showToast(it.message.toString())
                }
        }
    }

    private fun hideActionBar() {
        val actionBar: ActionBar? = (requireActivity() as? AppCompatActivity)?.supportActionBar
        actionBar?.hide()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}