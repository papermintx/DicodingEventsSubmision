package com.example.dicodingevent.data

import com.example.dicodingevent.data.dto.DetailEventDto
import com.example.dicodingevent.data.dto.DicodingEventDto
import com.example.dicodingevent.data.dto.EventDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("events")
    suspend fun getEvents(
        @Query("active") active: Int
    ) : DicodingEventDto


    @GET("events/{id}")
    suspend fun getEventDetail(
        @Path("id") id: Int
    ): DetailEventDto

}