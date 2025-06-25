package com.example.cutivatingapp1_java.model

import androidx.room.Entity
import java.time.LocalDateTime

@Entity(tableName = "Record_Time")
data class RecordTime (
    val  Id: Int=0,
    val  taskId:String,
    val  userId:Int,
    val  setTime: LocalDateTime,
    val  remainTime: LocalDateTime
)






