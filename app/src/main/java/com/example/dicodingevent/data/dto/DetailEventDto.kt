package com.example.dicodingevent.data.dto


import com.example.dicodingevent.domain.model.DicodingDetail
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

fun DetailEventDto.toDetailEvent(): DicodingDetail {
    return DicodingDetail(
        error = error,
        message = message,
        event = event.toEvent()
    )
}
