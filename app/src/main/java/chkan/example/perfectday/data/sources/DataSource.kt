package chkan.example.perfectday.data.sources

import chkan.example.perfectday.data.models.DataTask
import chkan.example.perfectday.domain.models.Task
import kotlinx.coroutines.flow.Flow

interface DataSource {
    fun getDailyTasksFlow(): Flow<List<DataTask>>

    fun getWeeklyTasksFlow(): Flow<List<DataTask>>

    suspend fun addDailyTask(task: DataTask)

    suspend fun deleteDailyTask(task: DataTask)
}