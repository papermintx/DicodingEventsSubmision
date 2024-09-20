package com.example.dicodingevent.data

import com.example.dicodingevent.data.dto.DicodingEventDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("events")
    suspend fun getEvents(
        @Query("active") active: Int
    ) : DicodingEventDto
}