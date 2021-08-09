package com.givekesh.places.domain.usecase

import com.givekesh.places.domain.entity.Place
import com.givekesh.places.domain.util.DataState
import kotlinx.coroutines.flow.Flow

interface PlacesUseCase {
    suspend operator fun invoke(): Flow<DataState<List<Place>>>
}