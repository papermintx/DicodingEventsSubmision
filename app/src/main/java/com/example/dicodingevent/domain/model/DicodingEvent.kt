package com.example.dicodingevent.domain.model

data class DicodingEvent(
    val error: Boolean,
    val message: String,
    val listEvents: List<Event>
)

