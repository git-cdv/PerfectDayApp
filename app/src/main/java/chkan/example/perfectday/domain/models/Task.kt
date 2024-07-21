package chkan.example.perfectday.domain.models

data class Task (val id: Int, val title: String, val status: TaskStatus, val updatedAt: String)

enum class TaskStatus {
    ACTIVE,
    DONE
}
