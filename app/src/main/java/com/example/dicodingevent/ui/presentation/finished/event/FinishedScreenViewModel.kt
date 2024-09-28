package com.example.dicodingevent.ui.presentation.finished.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dicodingevent.data.network.dto.toDicodingEvent
import com.example.dicodingevent.domain.model.Event
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
class FinishedScreenViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val _finishedEvent = MutableStateFlow<ResultState<List<Event>>>(ResultState.Idle)
    val finishedEvent = _finishedEvent.asStateFlow()

    fun getFinishedEvent() = viewModelScope.launch {
        useCase.getEventUseCase(0)
            .onStart {
                _finishedEvent.value = ResultState.Loading
            }
            .catch {
                _finishedEvent.value = ResultState.Error("Something went wrong when getting data")
            }
            .collect {
                val data = it.toDicodingEvent()
                _finishedEvent.value = ResultState.Success(data.listEvents)
            }
    }

}