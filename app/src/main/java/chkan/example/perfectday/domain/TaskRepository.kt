package chkan.example.perfectday.domain

import chkan.example.perfectday.data.models.DataTask
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    fun getDailyTasksFlow(): Flow<List<DataTask>>
    suspend fun getWeeklyTasksFlow(): Flow<List<DataTask>>
    suspend fun addDailyTask(task: DataTask)
    suspend fun deleteDailyTask(task: DataTask)
}