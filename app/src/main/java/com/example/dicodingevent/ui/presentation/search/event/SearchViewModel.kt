package com.example.dicodingevent.ui.presentation.search.event

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
class SearchViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {
    private val _searchResult = MutableStateFlow<ResultState<List<Event>>>(ResultState.Idle)
    val searchResult = _searchResult.asStateFlow()

    fun searchEvent(query: String) = viewModelScope.launch {
        useCase.searchEventUseCase(active =0, query = query)
            .onStart {
                _searchResult.value = ResultState.Loading
            }
            .catch {
                _searchResult.value = ResultState.Error("Something went wrong when searching event")
            }
            .collect {
                val data = it.toDicodingEvent()
                _searchResult.value = ResultState.Success(data.listEvents)
            }
    }

}