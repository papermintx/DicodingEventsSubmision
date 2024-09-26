package com.example.dicodingevent.domain.repository

import com.example.dicodingevent.domain.model.EventEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataRepository {
    suspend fun insertEvent(event : List<EventEntity>)
    suspend fun updateEvent(event : EventEntity)
    suspend fun getAllEvent() : List<EventEntity>
    suspend fun getEventById(id : Int) : EventEntity
    suspend fun getAllFavoriteEvent() : List<EventEntity>
}   // End of LocalDataRepository.kt