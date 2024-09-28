package com.example.dicodingevent.domain.usecase

import com.example.dicodingevent.domain.usecase.local.DeleteFavoriteEvent
import com.example.dicodingevent.domain.usecase.local.GetAllFavoriteEvents
import com.example.dicodingevent.domain.usecase.local.GetFavoriteEventById
import com.example.dicodingevent.domain.usecase.local.InsertFavoriteEvent
import com.example.dicodingevent.domain.usecase.network.GetEventDetailUseCase
import com.example.dicodingevent.domain.usecase.network.GetEventUseCase
import com.example.dicodingevent.domain.usecase.network.SearchEventUseCase

data class UseCase(
    val getEventUseCase: GetEventUseCase,
    val getEventDetailUseCase: GetEventDetailUseCase,
    val insertFavoriteEvent: InsertFavoriteEvent,
    val searchEventUseCase: SearchEventUseCase,
    val deleteFavoriteEvent: DeleteFavoriteEvent,
    val getFavoriteEventById: GetFavoriteEventById,
    val getAllFavoriteEvents: GetAllFavoriteEvents
)