package com.example.dicodingevent.data.repository

import com.example.dicodingevent.data.ApiService
import com.example.dicodingevent.data.dto.DetailEventDto
import com.example.dicodingevent.data.dto.DicodingEventDto
import com.example.dicodingevent.domain.repository.RemoteDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): RemoteDataRepository {
    override suspend fun getEvents(active: Int): DicodingEventDto {
        return withContext(Dispatchers.Default){
            apiService.getEvents(active)
        }
    }

    override suspend fun getEventDetail(id: Int): DetailEventDto {
        return  withContext(Dispatchers.Default){
            apiService.getEventDetail(id)
        }
    }
}