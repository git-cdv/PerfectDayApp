package chkan.example.perfectday.data

import chkan.example.perfectday.data.sources.DataSource
import chkan.example.perfectday.domain.models.Task
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    fun getDailyTasksFlow(): Flow<List<Task>>
    fun getWeeklyTasksFlow(): Flow<List<Task>>
}

class TasksRepositoryImpl(private val dataSource: DataSource): TasksRepository {

    override fun getDailyTasksFlow(): Flow<List<Task>> = dataSource.getDailyTasksFlow()

    override fun getWeeklyTasksFlow(): Flow<List<Task>> = dataSource.getWeeklyTasksFlow()

}