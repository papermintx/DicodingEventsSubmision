package com.example.dicodingevent.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dicodingevent.data.network.dto.toDicodingEvent
import com.example.dicodingevent.domain.model.DicodingEvent
import com.example.dicodingevent.domain.model.EventEntity
import com.example.dicodingevent.domain.model.toEntity
import com.example.dicodingevent.domain.usecase.UseCase
import com.example.dicodingevent.util.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: UseCase
): ViewModel() {
    private val _eventDicoding = MutableStateFlow<ResultState<DicodingEvent>>(ResultState.Idle)
    val eventDicoding = _eventDicoding.asStateFlow()

    private val _eventLocalDicoding = MutableStateFlow<ResultState<List<EventEntity>>>(ResultState.Idle)
    val eventLocalDicoding = _eventLocalDicoding.asStateFlow()


    private var isDataFetched = false


    fun getEventDicoding(active: Int) = viewModelScope.launch {
        useCase.getEventUsecase(active)
            .onStart {
                if (!isDataFetched) {
                    _eventDicoding.value = ResultState.Loading
                    isDataFetched = true
                }
            }
            .catch {error ->
                _eventDicoding.value = ResultState.Error(error)
            }
            .collect{event ->
                val result = event.toDicodingEvent()
                val resultData = result.listEvents.map {
                    it.toEntity()
                }
                insertAllEventLocal(resultData)
                getAllEventLocal()
            }
    }

    private fun insertAllEventLocal(event: List<EventEntity>) = viewModelScope.launch {
        useCase.insertAllEventLocalUseCase(event)
    }


    private fun getAllEventLocal() = viewModelScope.launch {
        useCase.getAllEventLocalUseCase()
            .onStart {
                _eventLocalDicoding.value = ResultState.Loading
            }
            .catch {error ->
                _eventLocalDicoding.value = ResultState.Error(error)
            }
            .collect {event ->
                _eventLocalDicoding.value = ResultState.Success(event)
            }
    }
}