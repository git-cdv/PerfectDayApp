package chkan.example.perfectday.data.sources.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import chkan.example.perfectday.data.models.DataTask
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyTasksDao {

    @Query("SELECT * FROM datatask")
    fun geTasksFlow(): Flow<List<DataTask>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(task: DataTask)

    @Delete
    suspend fun delete(task: DataTask)

}