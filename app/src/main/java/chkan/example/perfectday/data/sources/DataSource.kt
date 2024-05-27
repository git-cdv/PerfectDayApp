package chkan.example.perfectday.data.sources

import chkan.example.perfectday.domain.models.Task
import kotlinx.coroutines.flow.Flow

interface DataSource {
    fun getDailyTasksFlow(): Flow<List<Task>>

    fun getWeeklyTasksFlow(): Flow<List<Task>>
}