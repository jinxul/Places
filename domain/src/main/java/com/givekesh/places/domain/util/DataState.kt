package com.givekesh.places.domain.util

sealed class DataState<out R> {
    object Idle : DataState<Nothing>()
    object Loading : DataState<Nothing>()
    class Success<T>(val data: T) : DataState<T>()
    class Failed(val exception: Exception) : DataState<Nothing>()
}