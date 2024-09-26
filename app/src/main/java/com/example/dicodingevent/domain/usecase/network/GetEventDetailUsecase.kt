package com.example.dicodingevent.domain.usecase.network

import com.example.dicodingevent.data.network.dto.DetailEventDto
import com.example.dicodingevent.domain.repository.RemoteDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEventDetailUsecase  @Inject constructor(
    private val repository: RemoteDataRepository
) {
    suspend operator fun invoke(id: Int): Flow<DetailEventDto> = flow {
        emit(repository.getEventDetail(id = id))
    }
}