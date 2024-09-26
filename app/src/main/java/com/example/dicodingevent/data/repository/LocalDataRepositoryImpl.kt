package com.example.dicodingevent.data.repository

import com.example.dicodingevent.data.local.EventDao
import com.example.dicodingevent.domain.model.EventEntity
import com.example.dicodingevent.domain.repository.LocalDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class LocalDataRepositoryImpl @Inject constructor(
    private val eventDao: EventDao
): LocalDataRepository{
    override suspend fun insertEvent(event: List<EventEntity>) {
       return withContext(Dispatchers.IO){
           eventDao.insertEvents(event)
       }
    }

    override suspend fun updateEvent(event: EventEntity) {
        return withContext(Dispatchers.IO){
            eventDao.updateEvent(event)
        }
    }

    override suspend fun getAllEvent(): List<EventEntity> {
        return withContext(Dispatchers.IO){
            eventDao.getAllEvents()
        }
    }

    override suspend fun getEventById(id: Int): EventEntity {
        return withContext(Dispatchers.IO){
            eventDao.getEventById(id)
        }
    }

    override suspend fun getAllFavoriteEvent(): List<EventEntity> {
        return withContext(Dispatchers.IO){
            eventDao.getFavoriteEvents()
        }
    }

}