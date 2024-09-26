package com.example.dicodingevent.data.network.dto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DetailEventDto(
    @Json(name = "error")
    val error: Boolean,
    @Json(name = "message")
    val message: String,
    @Json(name = "event")
    val event: EventDto
)
