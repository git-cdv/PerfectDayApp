package chkan.example.perfectday.domain.models

import java.time.LocalDateTime


data class Task (val id: Int, val title: String, val status: TaskStatus, val updatedAt: LocalDateTime)

enum class TaskStatus {
    ACTIVE,
    DONE
}
