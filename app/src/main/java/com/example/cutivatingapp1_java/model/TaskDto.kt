package com.example.cutivatingapp1_java.model

import java.time.LocalDate

data class TaskDto (
    val taskName: String,
    val subtasksCount: Int,
    val startDate: LocalDate = LocalDate.now(),
    val deadlineDate: LocalDate,  // ❗️必须传
    val difficulty: String,
    val subject: String,
    val divisionType: String,
    val completed: Boolean,
    val subtasksCompletedCount: Int,
)
