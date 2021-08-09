package com.givekesh.places.domain.usecase

import com.givekesh.places.domain.entity.Place
import com.givekesh.places.domain.util.DataState
import kotlinx.coroutines.flow.Flow

interface SearchUseCase {
    suspend operator fun invoke(
        searchQuery: String,
        filterByFavorites: Boolean
    ): Flow<DataState<List<Place>>>
}