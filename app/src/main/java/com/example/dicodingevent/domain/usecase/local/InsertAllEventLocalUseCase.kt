package com.example.dicodingevent.domain.usecase.local

import com.example.dicodingevent.domain.model.EventEntity
import com.example.dicodingevent.domain.repository.LocalDataRepository
import javax.inject.Inject

class InsertAllEventLocalUseCase @Inject constructor(
    private val repository: LocalDataRepository
) {

    suspend operator fun invoke(event: List<EventEntity>) {
        repository.insertEvent(event)
    }
}