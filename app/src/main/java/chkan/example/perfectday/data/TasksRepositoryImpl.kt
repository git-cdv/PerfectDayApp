package chkan.example.perfectday.data

import chkan.example.perfectday.data.sources.DataSource
import chkan.example.perfectday.domain.models.Task
import chkan.example.perfectday.domain.TasksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TasksRepositoryImpl @Inject constructor (private val dataSource: DataSource):
    TasksRepository {

    override fun getDailyTasksFlow(): Flow<List<Task>> = dataSource.getDailyTasksFlow()

    override fun getWeeklyTasksFlow(): Flow<List<Task>> = dataSource.getWeeklyTasksFlow()

}