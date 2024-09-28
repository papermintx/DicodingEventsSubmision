package com.example.dicodingevent.data.network

import com.example.dicodingevent.data.network.dto.DetailEventDto
import com.example.dicodingevent.data.network.dto.DicodingEventDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("events")
    suspend fun getEvents(
        @Query("active") active: Int,
    ) : DicodingEventDto


    @GET("events")
    suspend fun searchEvent(
        @Query("active") active: Int,
        @Query("q") search: String
    ) : DicodingEventDto


    @GET("events/{id}")
    suspend fun getEventDetail(
        @Path("id") id: Int
    ): DetailEventDto

}