package chkan.example.perfectday.di

import chkan.example.perfectday.data.sources.DataSource
import chkan.example.perfectday.data.sources.DbSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SourcesModule {
    @Binds
    fun dbSource(impl: DbSourceImpl) : DataSource
}