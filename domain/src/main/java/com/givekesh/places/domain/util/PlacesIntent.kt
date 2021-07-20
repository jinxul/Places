package com.givekesh.places.domain.util

sealed class PlacesIntent {
    object GetPlaces : PlacesIntent()
    object GetFavorites : PlacesIntent()
    class SetFavorites(val id: Int, val isFavorite: Boolean) : PlacesIntent()
    class SearchPlaces(val searchQuery: String, val filterByFavorites: Boolean) : PlacesIntent()
}
