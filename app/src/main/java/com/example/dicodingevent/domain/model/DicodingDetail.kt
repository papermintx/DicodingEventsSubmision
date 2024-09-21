package com.example.dicodingevent.domain.model

import com.example.dicodingevent.data.dto.EventDto
import com.squareup.moshi.Json

data class DicodingDetail (
    val error: Boolean,
    val message: String,
    val event: Event
)