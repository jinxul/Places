package com.givekesh.places.domain.util

sealed class PlacesIntent {
    object GetPlaces : PlacesIntent()
    object GetFavorites : PlacesIntent()
    class SearchPlaces(val searchQuery: String, val filterByFavorites: Boolean) : PlacesIntent()
}
