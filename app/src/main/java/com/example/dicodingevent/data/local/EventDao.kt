package com.example.dicodingevent.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.dicodingevent.domain.model.EventEntity

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(events: EventEntity)

    @Query("SELECT * FROM eventsFavorite")
    suspend fun getAllFavoriteEvents(): List<EventEntity>

    @Query("SELECT * FROM eventsFavorite WHERE id = :id")
    suspend fun getFavoriteEventById(id: Int): EventEntity

    @Query("DELETE FROM eventsFavorite WHERE id = :id")
    suspend fun deleteEvent(id: Int)


}