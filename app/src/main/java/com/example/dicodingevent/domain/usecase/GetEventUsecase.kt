package com.example.dicodingevent.domain.usecase

import com.example.dicodingevent.data.dto.DicodingEventDto
import com.example.dicodingevent.domain.repository.RemoteDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEventUsecase @Inject constructor(
    private val repository: RemoteDataRepository
) {

    suspend operator fun invoke(active: Int) : Flow<DicodingEventDto> = flow{
        emit(repository.getEvents(active))
    }
}