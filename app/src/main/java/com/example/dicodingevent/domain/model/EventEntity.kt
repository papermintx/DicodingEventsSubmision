package com.example.dicodingevent.domain.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "eventsFavorite")
data class EventEntity(
    @PrimaryKey val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "mediaCover")
    val mediaCover: String,

    @ColumnInfo(name = "endTime")
    val endTime: String,
)
