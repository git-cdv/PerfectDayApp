package chkan.example.perfectday.domain.usecases.daily_tasks

import chkan.example.perfectday.data.models.DataTask
import chkan.example.perfectday.domain.TasksRepository
import chkan.example.perfectday.domain.models.TaskStatus
import java.time.LocalDateTime
import javax.inject.Inject

class AddDailyTaskUseCase @Inject constructor(
    private val tasksRepository: TasksRepository,
) {
    suspend fun run(title: String, now: LocalDateTime) {
        tasksRepository.addDailyTask(
            DataTask(
                title = title,
                status = TaskStatus.ACTIVE,
                updatedAt = now
            )
        )
    }
}