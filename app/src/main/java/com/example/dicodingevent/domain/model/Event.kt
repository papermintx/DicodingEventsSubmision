package com.example.dicodingevent.domain.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Event(
    val id: Int,
    val name: String,
    val summary: String,
    val description: String,
    val imageLogo: String,
    val mediaCover: String,
    val category: String,
    val ownerName: String,
    val cityName: String,
    val quota: Int,
    val registrants: Int,
    val beginTime: String,
    val endTime: String,
    val link: String
): Parcelable

fun Event.toEntity(): EventEntity {
    return EventEntity(
        id = id,
        name = name,
        mediaCover = mediaCover,
        endTime = endTime,
    )
}