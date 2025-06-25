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
        binding = SubmitTaskBinding.inflate(inflater, R.layout.submit_task, container, false)
        binding.viewModel  = taskSubmitViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

}