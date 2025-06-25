package com.example.cutivatingapp1_java.model

import android.net.Uri
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.cutivatingapp1_java.utils.UriConverter

@Entity(
    tableName = "task",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
@TypeConverters(UriConverter::class)  // 自定义类型转换器
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val userId: Int,
    val taskName: String,
    val taskType: String,
    val taskContent: String,
    val setTime: String,
    val remainTime: String,
    val imageUrl: Uri
)