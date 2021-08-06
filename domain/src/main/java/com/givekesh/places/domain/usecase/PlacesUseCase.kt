package com.givekesh.places.domain.usecase

import android.util.Log
import com.givekesh.places.data.source.repository.PlacesRepository
import com.givekesh.places.domain.entity.Place
import com.givekesh.places.domain.mapper.CachedPlaceMapper
import com.givekesh.places.domain.mapper.PlaceMapper
import com.givekesh.places.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlacesUseCase @Inject constructor(
    private val placesRepository: PlacesRepository,
    private val cachedMapper: CachedPlaceMapper,
    private val mapper: PlaceMapper
) {
    suspend operator fun invoke(): Flow<DataState<List<Place>>> = flow {
        emit(DataState.Loading)
        try {
            val favoritesId = when (placesRepository.isInitialSetup()) {
                true -> placesRepository.getFavorites().favoriteIds
                false -> placesRepository.getCachedFavoriteIds()
            }
            val places = placesRepository.getPlaces().placeResponses
            val promotedPlaces = placesRepository.getPromotedPlaces().placeResponses
            val mappedPlaces = cachedMapper.mapToEntityList(
                models = places,
                favorites = favoritesId,
                isPromoted = false
            )
            val mappedPromotedPlaces = cachedMapper.mapToEntityList(
                models = promotedPlaces,
                favorites = favoritesId,
                isPromoted = true
            )

            val combinedData = listOf(mappedPromotedPlaces, mappedPlaces).flatten()
            placesRepository.insertCachedPlaces(combinedData)
            val data = mapper.mapToEntityList(combinedData)
            placesRepository.finishInitialSetup()
            emit(DataState.Success(data))
        } catch (exception: Exception) {
            Log.e("PlacesUseCase", "Failed", exception)
            val cachedData = placesRepository.getCachedPlaces()
            if (cachedData.isNotEmpty()) {
                val data = mapper.mapToEntityList(cachedData)
                emit(DataState.Success(data))
            } else {
                emit(DataState.Failed(exception))
            }
        }
    }
}