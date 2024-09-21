package com.example.dicodingevent.presentation.detailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dicodingevent.data.dto.toEvent
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
class DetailViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {
    private val _eventDetailDicoding = MutableStateFlow<ResultState<Event>>(ResultState.Idle)
    val eventDetailDicoding = _eventDetailDicoding.asStateFlow()

    private var isDataFetched = false

    fun getEventDetailDicoding(id: Int)  = viewModelScope.launch {
        useCase.getEventDetailUsecase(id)
            .onStart {
                if (!isDataFetched) {
                    _eventDetailDicoding.value = ResultState.Loading
                    isDataFetched = true
                }
            }
            .catch { error ->
                _eventDetailDicoding.value = ResultState.Error(error)
            }
            .collect { event ->
                val result = event.event.toEvent()
                _eventDetailDicoding.value = ResultState.Success(result)
            }
        }

}

