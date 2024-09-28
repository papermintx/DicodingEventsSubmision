package com.example.dicodingevent.domain.usecase.local

import com.example.dicodingevent.domain.repository.LocalDataRepository
import javax.inject.Inject

class DeleteFavoriteEvent @Inject constructor(
    private val repository: LocalDataRepository
) {
    suspend operator fun invoke(eventId: Int) {
        repository.deleteEvent(eventId)
    }
}