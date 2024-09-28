package com.example.dicodingevent.domain.usecase.network

import com.example.dicodingevent.data.network.dto.DicodingEventDto
import com.example.dicodingevent.domain.repository.RemoteDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchEventUseCase @Inject constructor(
    private val repository: RemoteDataRepository
) {

    suspend operator fun invoke(active: Int,query: String) : Flow<DicodingEventDto> = flow{
        emit(repository.searchEvents(active,query))
    }
}