package com.example.dicodingevent.domain.repository

import com.example.dicodingevent.data.network.dto.DetailEventDto
import com.example.dicodingevent.data.network.dto.DicodingEventDto

interface RemoteDataRepository {
    suspend fun getEvents(active: Int): DicodingEventDto

    suspend fun getEventDetail(id: Int): DetailEventDto
}