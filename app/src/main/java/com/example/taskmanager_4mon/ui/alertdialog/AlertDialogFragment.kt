package com.example.taskmanager_4mon.ui.alertdialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taskmanager_4mon.R
import com.example.taskmanager_4mon.databinding.FragmentAlertDiaologBinding

class AlertDialogFragment : Fragment() {

    private lateinit var binding: FragmentAlertDiaologBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alert_diaolog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}