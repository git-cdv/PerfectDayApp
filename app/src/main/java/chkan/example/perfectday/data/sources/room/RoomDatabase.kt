package chkan.example.perfectday.data.sources.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import chkan.example.perfectday.data.models.DataTask

@Database(entities = [DataTask::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class RoomDatabase: RoomDatabase() {
    abstract val dailyTasksDao: DailyTasksDao
}