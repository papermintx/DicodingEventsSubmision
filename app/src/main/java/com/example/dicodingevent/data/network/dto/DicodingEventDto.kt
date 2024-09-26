package com.example.dicodingevent.data.network.dto


import com.example.dicodingevent.domain.model.DicodingEvent
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DicodingEventDto(
    @Json(name = "error")
    val error: Boolean,
    @Json(name = "message")
    val message: String,
    @Json(name = "listEvents")
    val listEvents: List<EventDto>
)

fun DicodingEventDto.toDicodingEvent(): DicodingEvent{
    return DicodingEvent(
        error = error,
        message = message,
        listEvents = List(listEvents.size) { event ->
            listEvents[event].toEvent()
        }
    )
}
