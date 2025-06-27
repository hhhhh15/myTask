package com.example.cutivatingapp1_java.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cutivatingapp1_java.ViewModel.TaskSubmitViewModel
import com.example.cutivatingapp1_java.databinding.SubmitTaskBinding

class TaskSubmitFragment : Fragment() {
    private lateinit var binding: SubmitTaskBinding
    private val taskSubmitViewModel :TaskSubmitViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
            binding = SubmitTaskBinding.inflate(inflater, container, false)
            binding.lifecycleOwner = viewLifecycleOwner
            binding.submitVm = taskSubmitViewModel
        return binding.root
    }

}