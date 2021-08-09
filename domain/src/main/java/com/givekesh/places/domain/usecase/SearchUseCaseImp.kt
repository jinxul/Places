package com.givekesh.places.domain.usecase

import com.givekesh.places.data.source.repository.PlacesRepository
import com.givekesh.places.domain.entity.Place
import com.givekesh.places.domain.mapper.PlaceMapper
import com.givekesh.places.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchUseCaseImp @Inject constructor(
    private val placesRepository: PlacesRepository,
    private val mapper: PlaceMapper
) : SearchUseCase {
    override suspend operator fun invoke(
        searchQuery: String,
        filterByFavorites: Boolean
    ): Flow<DataState<List<Place>>> = flow {
        val data = when (filterByFavorites) {
            true -> placesRepository.getCachedFavoritePlaces(searchQuery)
            false -> placesRepository.getCachedPlaces(searchQuery)
        }
        val mappedData = mapper.mapToEntityList(data)
        emit(DataState.Success(mappedData))
    }
}