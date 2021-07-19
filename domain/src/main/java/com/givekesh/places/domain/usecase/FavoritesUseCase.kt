package com.givekesh.places.domain.usecase

import com.givekesh.places.data.source.repository.PlacesRepository
import com.givekesh.places.domain.entity.Place
import com.givekesh.places.domain.mapper.PlaceMapper
import com.givekesh.places.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoritesUseCase @Inject constructor(
    private val placesRepository: PlacesRepository,
    private val mapper: PlaceMapper
) {
    suspend operator fun invoke(): Flow<DataState<List<Place>>> = flow {
        emit(DataState.Loading)
        try {
            val data = placesRepository.getFavoritePlaces()
            val mappedData = mapper.mapToEntityList(data)
            emit(DataState.Success(mappedData))
        } catch (exception: Exception) {
            emit(DataState.Failed(exception))
        }
    }
}