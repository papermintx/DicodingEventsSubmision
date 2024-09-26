package com.example.dicodingevent.presentation.detailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dicodingevent.data.network.dto.toEvent
import com.example.dicodingevent.domain.model.Event
import com.example.dicodingevent.domain.model.EventEntity
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
class DetailViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val _eventDetailLocalDicoding = MutableStateFlow<ResultState<EventEntity>>(ResultState.Idle)
    val eventDetailLocalDicoding = _eventDetailLocalDicoding.asStateFlow()

    fun getEvenLocaltById(id: Int) = viewModelScope.launch {
        useCase.getEventLocalByIdUseCase(id)
            .onStart {
                _eventDetailLocalDicoding.value = ResultState.Loading
            }
            .catch { error ->
                _eventDetailLocalDicoding.value = ResultState.Error(error)
            }
            .collect { event ->
                _eventDetailLocalDicoding.value = ResultState.Success(event)
            }

    }

}

