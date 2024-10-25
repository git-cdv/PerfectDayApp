package chkan.example.perfectday.data.sources

import chkan.example.perfectday.data.models.DataTask
import chkan.example.perfectday.data.sources.room.DailyTasksDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DbSourceImpl @Inject constructor (private val dailyTasksDao: DailyTasksDao): DataSource {
    override fun getDailyTasksFlow(): Flow<List<DataTask>> {
        return dailyTasksDao.geTasksFlow()
    }

    override fun getWeeklyTasksFlow(): Flow<List<DataTask>> {
        return flowOf(listOf())
    }

    override suspend fun addDailyTask(task: DataTask) {
        dailyTasksDao.add(task)
    }

    override suspend fun deleteDailyTask(task: DataTask) {
        dailyTasksDao.delete(task)
    }
}