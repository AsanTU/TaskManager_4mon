package com.example.taskmanager_4mon.ui.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.taskmanager_4mon.App
import com.example.taskmanager_4mon.R
import com.example.taskmanager_4mon.data.local.Pref
import com.example.taskmanager_4mon.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    private val PICK_IMAGE_REQUEST = 1

    private val pref by lazy {
        Pref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideActionBar()
        saveData()
        binding.profileImg.setOnClickListener {
            pickImageFromGallery()
        }
        exitAlertDialog()
    }

    private fun exitAlertDialog() {
        binding.ivExit.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.exit_text))
                .setMessage(getString(R.string.exit_confirmation))
                .setPositiveButton(getString(R.string.delete_accept)) { _, _ ->
                    findNavController().navigate(R.id.phoneFragment)
                }
                .setNegativeButton(getString(R.string.cancel)) { _, _ -> }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            val imageUri: Uri? = data.data
            pref.saveImageUri(imageUri.toString())
            binding.profileImg.setImageURI(imageUri)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun saveData() {
        binding.etProfile.setText(pref.getText())
        binding.btnSave.setOnClickListener {
            pref.saveText(binding.etProfile.text.toString())
        }
    }

    private fun hideActionBar() {
        val actionBar: ActionBar? = (requireActivity() as? AppCompatActivity)?.supportActionBar
        actionBar?.hide()
    }

}