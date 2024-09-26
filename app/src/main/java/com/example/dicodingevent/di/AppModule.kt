package com.example.dicodingevent.di

import com.example.dicodingevent.domain.usecase.UseCase
import com.example.dicodingevent.domain.usecase.local.GetAllEventLocalFavorite
import com.example.dicodingevent.domain.usecase.local.GetAllEventLocalUseCase
import com.example.dicodingevent.domain.usecase.local.GetEventLocalByIdUseCase
import com.example.dicodingevent.domain.usecase.local.InsertAllEventLocalUseCase
import com.example.dicodingevent.domain.usecase.local.UpdateEventLocalUseCase
import com.example.dicodingevent.domain.usecase.network.GetEventDetailUsecase
import com.example.dicodingevent.domain.usecase.network.GetEventUsecase
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
        getEventUsecase: GetEventUsecase,
        getEventDetailUsecase: GetEventDetailUsecase,
        getAllEventLocalUseCase: GetAllEventLocalUseCase,
        insertAllEventLocalUseCase: InsertAllEventLocalUseCase,
        getAllEventLocalFavorite: GetAllEventLocalFavorite,
        getEventLocalByIdUseCase: GetEventLocalByIdUseCase,
        updateEventLocalUseCase: UpdateEventLocalUseCase
    ): UseCase {
        return UseCase(
            getEventUsecase,
            getEventDetailUsecase,
            getAllEventLocalUseCase,
            updateEventLocalUseCase,
            getEventLocalByIdUseCase,
            getAllEventLocalFavorite,
            insertAllEventLocalUseCase
        )
    }
}