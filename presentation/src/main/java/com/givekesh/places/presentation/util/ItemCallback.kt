package com.givekesh.places.presentation.util

fun interface ItemFavoriteCallback {
    fun onFavoritesChanged(id: Int, isFavorite: Boolean)
}