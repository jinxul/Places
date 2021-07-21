package com.givekesh.places.presentation.util

import com.givekesh.places.domain.entity.Place

interface ItemCallback {
    fun onFavoritesChanged(id: Int, isFavorite: Boolean)
    fun onClickListener(place: Place)
}