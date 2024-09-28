package com.example.dicodingevent.domain.repository

import com.example.dicodingevent.domain.model.EventEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataRepository {
    suspend fun insertFavoriteEvent(event : EventEntity)
    suspend fun deleteEvent(id : Int)
    suspend fun getAllFavoriteEvents() : List<EventEntity>
    suspend fun getEventById(id : Int) : EventEntity
}