package com.example.dicodingevent.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dicodingevent.data.dto.toDicodingEvent
import com.example.dicodingevent.domain.model.DicodingEvent
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

    init {
        getEventDicoding(0)
    }

    fun getEventDicoding(active: Int) = viewModelScope.launch {
        useCase.getEventUsecase(active)
            .onStart {
                _eventDicoding.value = ResultState.Loading
            }
            .catch {error ->
                _eventDicoding.value = ResultState.Error(error)
            }
            .collect{event ->
                val result = event.toDicodingEvent()
                _eventDicoding.value = ResultState.Success(result)
            }
    }
}