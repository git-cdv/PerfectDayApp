package chkan.example.perfectday.domain.models

open class Task (val id: Int, val title: String, val status: TaskStatus)

enum class TaskStatus {
    ACTIVE,
    DONE
}
