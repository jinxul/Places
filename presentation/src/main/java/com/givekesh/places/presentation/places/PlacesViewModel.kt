package com.givekesh.places.presentation.places

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.givekesh.places.domain.entity.Place
import com.givekesh.places.domain.usecase.FavoritesUseCase
import com.givekesh.places.domain.usecase.PlacesUseCase
import com.givekesh.places.domain.usecase.SearchUseCase
import com.givekesh.places.domain.util.DataState
import com.givekesh.places.domain.util.PlacesIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val placesUseCase: PlacesUseCase,
    private val favoritesUseCase: FavoritesUseCase,
    private val searchUseCase: SearchUseCase
) : ViewModel() {
    val channel = Channel<PlacesIntent>(Channel.RENDEZVOUS)

    private val _dataState = MutableStateFlow<DataState<List<Place>>>(DataState.Idle)
    val dataState: StateFlow<DataState<List<Place>>> get() = _dataState

    private var currentJob: Job? = null

    init {
        handleIntents()
    }

    private fun handleIntents() {
        viewModelScope.launch {
            channel.consumeAsFlow().collect { placesIntent ->
                currentJob?.cancel()
                when (placesIntent) {
                    PlacesIntent.GetPlaces -> currentJob = placesUseCase()
                        .onEach { _dataState.value = it }
                        .launchIn(viewModelScope)
                    PlacesIntent.GetFavorites -> currentJob = favoritesUseCase.getFavoritePlaces()
                        .onEach { _dataState.value = it }
                        .launchIn(viewModelScope)
                    is PlacesIntent.SetFavorites -> favoritesUseCase.setFavoritePlaces(
                        id = placesIntent.id,
                        isFavorite = placesIntent.isFavorite
                    )
                    is PlacesIntent.SearchPlaces -> currentJob = searchUseCase(
                        searchQuery = placesIntent.searchQuery,
                        filterByFavorites = placesIntent.filterByFavorites
                    )
                        .onEach { _dataState.value = it }
                        .launchIn(viewModelScope)
                }
            }
        }
    }
}