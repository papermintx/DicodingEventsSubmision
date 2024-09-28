package com.example.dicodingevent.ui.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dicodingevent.data.network.dto.toEvent
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
class HomeViewModel @Inject constructor(
    private val useCase: UseCase
): ViewModel() {

    private val _eventUpcoming = MutableStateFlow<ResultState<List<Event>>>(ResultState.Idle)
    val eventUpcoming = _eventUpcoming.asStateFlow()

    private val _eventFinished = MutableStateFlow<ResultState<List<Event>>>(ResultState.Idle)
    val eventFinished = _eventFinished.asStateFlow()

    private var isDataFetched = false

    fun getEventForHome() = viewModelScope.launch {
        if (!isDataFetched) {
            isDataFetched = true

            useCase.getEventUseCase(1)
                .onStart {
                    _eventUpcoming.value = ResultState.Loading
                }
                .catch {
                    _eventUpcoming.value = ResultState.Error("Something went wrong")
                }
                .collect { event ->
                    val data = event.listEvents.map {
                        it.toEvent()
                    }.take(5)
                    _eventUpcoming.value = ResultState.Success(data)
                }

            useCase.getEventUseCase(0)
                .onStart {
                    _eventFinished.value = ResultState.Loading
                }
                .catch {
                    _eventFinished.value = ResultState.Error("Something went wrong")
                }
                .collect { event ->
                    val data = event.listEvents.map {
                        it.toEvent()
                    }.take(5)
                    _eventFinished.value = ResultState.Success(data)
                }
        }
    }
}
