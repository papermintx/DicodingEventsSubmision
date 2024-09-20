package com.example.dicodingevent.domain.repository

import com.example.dicodingevent.data.dto.DicodingEventDto

interface RemoteDataRepository {
    suspend fun getEvents(active: Int): DicodingEventDto
}