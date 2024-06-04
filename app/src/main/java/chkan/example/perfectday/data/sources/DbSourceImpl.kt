package chkan.example.perfectday.data.sources

import chkan.example.perfectday.domain.models.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DbSourceImpl @Inject constructor (): DataSource {
    override fun getDailyTasksFlow(): Flow<List<Task>> {
        return flowOf(listOf())
    }

    override fun getWeeklyTasksFlow(): Flow<List<Task>> {
        return flowOf(listOf())
    }
}