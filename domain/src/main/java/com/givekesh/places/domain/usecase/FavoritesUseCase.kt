package com.givekesh.places.domain.usecase

import com.givekesh.places.domain.entity.Place
import com.givekesh.places.domain.util.DataState
import kotlinx.coroutines.flow.Flow

interface FavoritesUseCase {
    suspend fun getFavoritePlaces(): Flow<DataState<List<Place>>>

    suspend fun setFavoritePlaces(id: Int, isFavorite: Boolean)
}