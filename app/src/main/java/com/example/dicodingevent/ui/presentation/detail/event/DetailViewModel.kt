package com.example.dicodingevent.ui.presentation.detail.event


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dicodingevent.data.network.dto.toEvent
import com.example.dicodingevent.domain.model.Event
import com.example.dicodingevent.domain.model.toEntity
import com.example.dicodingevent.domain.usecase.UseCase
import com.example.dicodingevent.util.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {
    private val _eventDetail = MutableStateFlow<ResultState<Event>>(ResultState.Idle)
    val eventDetail = _eventDetail.asStateFlow()

    private val _eventFavorite = MutableStateFlow(false)
    val eventFavorite = _eventFavorite.asStateFlow()

    private fun getEventFavorite(id: Int) = viewModelScope.launch {
        useCase.getFavoriteEventById(id)
            .collect { data ->
                _eventFavorite.value = data != null
            }
    }

    fun saveEventFavorite(event: Event?) = viewModelScope.launch {
        event?.let {
            val data = event.toEntity()
            useCase.insertFavoriteEvent(eventEntity = data)
            getEventFavorite(event.id)
        }
    }

    fun deleteEventFavorite(id: Int) = viewModelScope.launch {
        useCase.deleteFavoriteEvent(eventId = id)
        getEventFavorite(id)
    }

    fun getEventDetail(id: Int) = viewModelScope.launch {
        useCase.getEventDetailUseCase(id)
            .onStart {
                _eventDetail.value = ResultState.Loading
                getEventFavorite(id)
            }
            .catch {
                _eventDetail.value = ResultState.Error("Something went wrong when get event detail")
            }
            .collect { dataEvent ->
                val data = dataEvent.event.toEvent()
                _eventDetail.value = ResultState.Success(data)
            }
    }
}
