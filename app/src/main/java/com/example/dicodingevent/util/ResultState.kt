package com.example.dicodingevent.util

sealed interface ResultState<T> {
    data object Idle: ResultState<Nothing>
    data object Loading: ResultState<Nothing>
    data class Success<T>(val data: T): ResultState<T>
    data class Error(val exception: Throwable): ResultState<Nothing>
}