package chkan.example.perfectday.domain.usecases.daily_tasks

import chkan.example.perfectday.data.models.toTask
import chkan.example.perfectday.domain.TasksRepository
import chkan.example.perfectday.domain.models.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetDailyTasksUseCase @Inject constructor(
    private val tasksRepository: TasksRepository,
) {
    fun run(): Flow<List<Task>> {
        return tasksRepository.getDailyTasksFlow().map { it.map { it.toTask() } }
    }
}