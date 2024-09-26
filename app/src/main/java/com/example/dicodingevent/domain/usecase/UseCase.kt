package com.example.dicodingevent.domain.usecase

import com.example.dicodingevent.domain.usecase.local.GetAllEventLocalFavorite
import com.example.dicodingevent.domain.usecase.local.GetAllEventLocalUseCase
import com.example.dicodingevent.domain.usecase.local.GetEventLocalByIdUseCase
import com.example.dicodingevent.domain.usecase.local.InsertAllEventLocalUseCase
import com.example.dicodingevent.domain.usecase.local.UpdateEventLocalUseCase
import com.example.dicodingevent.domain.usecase.network.GetEventDetailUsecase
import com.example.dicodingevent.domain.usecase.network.GetEventUsecase

data class UseCase(
    val getEventUsecase: GetEventUsecase,
    val getEventDetailUsecase: GetEventDetailUsecase,
    val getAllEventLocalUseCase: GetAllEventLocalUseCase,
    val updateEventLocalUseCase: UpdateEventLocalUseCase,
    val getEventLocalByIdUseCase: GetEventLocalByIdUseCase,
    val getAllEventLocalFavorite: GetAllEventLocalFavorite,
    val insertAllEventLocalUseCase: InsertAllEventLocalUseCase
)