package com.example.dicodingevent.domain.usecase.local

import com.example.dicodingevent.domain.model.EventEntity
import com.example.dicodingevent.domain.repository.LocalDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoriteEventById @Inject constructor(
    private val repository: LocalDataRepository
) {
    suspend operator fun invoke(id: Int) : Flow<EventEntity> = flow {
        emit(repository.getEventById(id))
    }
}