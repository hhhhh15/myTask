package com.example.cutivatingapp1_java.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import java.time.LocalDate

class TaskSubmitViewModel(application: Application) : AndroidViewModel(application) {

    val taskName = MutableLiveData<String>()
    val taskCount = MutableLiveData<Int>()
    val startTime = MutableLiveData<LocalDate>()
    val endTime = MutableLiveData<LocalDate>()
    val taskDifficulty = MutableLiveData<String>()
    val taskSubject = MutableLiveData<String>()
    val taskType = MutableLiveData<String>()
}