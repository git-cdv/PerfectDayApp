package chkan.example.perfectday.di

import android.content.Context
import androidx.room.Room
import chkan.example.perfectday.data.sources.room.DailyTasksDao
import chkan.example.perfectday.data.sources.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): RoomDatabase {
        return Room
            .databaseBuilder(
                appContext,
                RoomDatabase::class.java,
                "tasks")
            .build()
    }

    @Provides
    fun provideHistoryDao(db: RoomDatabase): DailyTasksDao {
        return db.dailyTasksDao
    }

}