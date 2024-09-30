package com.example.dicodingevent.ui.presentation.upcomming.event

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
class UpcomingViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel(){
    private val _upcomingEvent = MutableStateFlow<ResultState<List<Event>>>(ResultState.Idle)
    val upcomingEvent = _upcomingEvent.asStateFlow()

    private var hasFetchedData = false

    fun getUpcomingEvent() = viewModelScope.launch {
        if (!hasFetchedData) {
            useCase.getEventUseCase(1)
                .onStart {
                    _upcomingEvent.value = ResultState.Loading
                }
                .catch {
                    _upcomingEvent.value = ResultState.Error("Something went wrong when getting data")
                }
                .collect {
                    val data = it.toDicodingEvent()
                    _upcomingEvent.value = ResultState.Success(data.listEvents)
                    hasFetchedData = true
                }
        }
    }
}