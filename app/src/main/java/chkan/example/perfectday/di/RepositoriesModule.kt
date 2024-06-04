package chkan.example.perfectday.di

import chkan.example.perfectday.data.TasksRepositoryImpl
import chkan.example.perfectday.domain.TasksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {
    @Binds
    fun tasksRepository(impl: TasksRepositoryImpl) : TasksRepository
}