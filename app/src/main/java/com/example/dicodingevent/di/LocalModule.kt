package com.example.dicodingevent.di

import android.content.Context
import androidx.room.Room
import com.example.dicodingevent.data.local.EventDao
import com.example.dicodingevent.data.local.EventDatabase
import com.example.dicodingevent.data.repository.LocalDataRepositoryImpl
import com.example.dicodingevent.domain.repository.LocalDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideLocalDataRepository(eventDao: EventDao): LocalDataRepository {
        return LocalDataRepositoryImpl(eventDao)
    }


    @Provides
    @Singleton
    fun provideEventDatabase(@ApplicationContext context: Context): EventDatabase {
        return Room.databaseBuilder(
            context,
            EventDatabase::class.java,
            "event_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideEventDao(database: EventDatabase) : EventDao {
        return database.eventDao()
    }

}