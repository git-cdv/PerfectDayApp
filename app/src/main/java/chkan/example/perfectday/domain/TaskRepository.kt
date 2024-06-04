package chkan.example.perfectday.domain

import chkan.example.perfectday.domain.models.Task
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    fun getDailyTasksFlow(): Flow<List<Task>>
    fun getWeeklyTasksFlow(): Flow<List<Task>>
}