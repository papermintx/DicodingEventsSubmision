package com.example.dicodingevent.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.dicodingevent.domain.model.EventEntity

@Dao
interface EventDao {
    @Query("SELECT * FROM events")
    suspend fun getAllEvents(): List<EventEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(events: List<EventEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateEvent(event: EventEntity)

    @Query("SELECT * FROM events WHERE isFavorite = 1")
    suspend fun getFavoriteEvents(): List<EventEntity>

    @Query("SELECT * FROM events WHERE id = :id")
    suspend fun getEventById(id: Int): EventEntity
}