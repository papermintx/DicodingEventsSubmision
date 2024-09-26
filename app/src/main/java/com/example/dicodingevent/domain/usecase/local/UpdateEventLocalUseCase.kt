package com.example.dicodingevent.domain.usecase.local

import com.example.dicodingevent.domain.model.EventEntity
import com.example.dicodingevent.domain.repository.LocalDataRepository
import javax.inject.Inject

class UpdateEventLocalUseCase @Inject constructor(
    private val repository: LocalDataRepository
) {
    suspend operator fun invoke(eventEntity: EventEntity){
        repository.updateEvent(eventEntity)
    }
}