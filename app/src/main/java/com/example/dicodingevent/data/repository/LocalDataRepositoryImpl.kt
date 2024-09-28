package com.example.dicodingevent.data.repository

import com.example.dicodingevent.data.local.EventDao
import com.example.dicodingevent.domain.model.EventEntity
import com.example.dicodingevent.domain.repository.LocalDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class LocalDataRepositoryImpl @Inject constructor(
    private val eventDao: EventDao
): LocalDataRepository {
    override suspend fun insertFavoriteEvent(event: EventEntity) {
        return withContext(Dispatchers.IO){
            eventDao.insertEvents(event)
        }
    }


    override suspend fun deleteEvent(id: Int) {
        return withContext(Dispatchers.IO){
            eventDao.deleteEvent(id)
        }
    }

    override suspend fun getAllFavoriteEvents(): List<EventEntity> {
        return withContext(Dispatchers.IO){
            eventDao.getAllFavoriteEvents()
        }
    }

    override suspend fun getEventById(id: Int): EventEntity {
        return withContext(Dispatchers.IO){
            eventDao.getFavoriteEventById(id)

        }
    }


}