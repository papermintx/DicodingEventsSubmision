package com.example.dicodingevent.di

import com.example.dicodingevent.domain.usecase.UseCase
import com.example.dicodingevent.domain.usecase.local.DeleteFavoriteEvent
import com.example.dicodingevent.domain.usecase.local.GetAllFavoriteEvents
import com.example.dicodingevent.domain.usecase.local.GetFavoriteEventById
import com.example.dicodingevent.domain.usecase.local.InsertFavoriteEvent
import com.example.dicodingevent.domain.usecase.network.GetEventDetailUseCase
import com.example.dicodingevent.domain.usecase.network.GetEventUseCase
import com.example.dicodingevent.domain.usecase.network.SearchEventUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUseCase(
        getEventUseCase: GetEventUseCase,
        getEventDetailUseCase: GetEventDetailUseCase,
        insertFavoriteEvent: InsertFavoriteEvent,
        deleteFavoriteEvent: DeleteFavoriteEvent,
        getAllFavoriteEvents: GetAllFavoriteEvents,
        getFavoriteEventById: GetFavoriteEventById,
        searchEventUseCase: SearchEventUseCase,

        ): UseCase {
        return UseCase(
            getEventUseCase,
            getEventDetailUseCase,
            insertFavoriteEvent,
            searchEventUseCase,
            deleteFavoriteEvent,
            getFavoriteEventById,
            getAllFavoriteEvents
        )
    }


}