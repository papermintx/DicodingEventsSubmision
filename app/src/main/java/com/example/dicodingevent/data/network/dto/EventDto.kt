package com.example.dicodingevent.data.network.dto


import com.example.dicodingevent.domain.model.Event
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "summary")
    val summary: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "imageLogo")
    val imageLogo: String,
    @Json(name = "mediaCover")
    val mediaCover: String,
    @Json(name = "category")
    val category: String,
    @Json(name = "ownerName")
    val ownerName: String,
    @Json(name = "cityName")
    val cityName: String,
    @Json(name = "quota")
    val quota: Int,
    @Json(name = "registrants")
    val registrants: Int,
    @Json(name = "beginTime")
    val beginTime: String,
    @Json(name = "endTime")
    val endTime: String,
    @Json(name = "link")
    val link: String
)

fun EventDto.toEvent(): Event{
    return Event(
        id = id,
        name = name,
        summary = summary,
        description = description,
        imageLogo = imageLogo,
        mediaCover = mediaCover,
        category = category,
        ownerName = ownerName,
        cityName = cityName,
        quota = quota,
        registrants = registrants,
        beginTime = beginTime,
        endTime = endTime,
        link = link
    )
}