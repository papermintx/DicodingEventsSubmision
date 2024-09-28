package com.example.dicodingevent.ui.presentation.favorite.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class FavoriteViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {
    private val _eventFavorite = MutableStateFlow<ResultState<List<EventEntity>>> (ResultState.Idle)
    val eventFavorite = _eventFavorite.asStateFlow()

    fun getFavoriteEvent() = viewModelScope.launch {
        useCase.getAllFavoriteEvents()
            .onStart {
                _eventFavorite.value = ResultState.Loading
            }
            .catch {
                _eventFavorite.value = ResultState.Error("Something went wrong when getting favorite event")
            }
            .collect {
                _eventFavorite.value = ResultState.Success(it)
            }
    }
}