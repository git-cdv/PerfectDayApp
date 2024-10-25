package chkan.example.perfectday.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import chkan.example.perfectday.domain.models.Task
import chkan.example.perfectday.domain.models.TaskStatus
import java.time.LocalDateTime

@Entity
data class DataTask (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "status")
    val status: TaskStatus,
    @ColumnInfo(name = "updated_at")
    val updatedAt: LocalDateTime
)

fun DataTask.toTask(): Task {
    return Task(id = id, title = title, status = status, updatedAt = updatedAt)
}