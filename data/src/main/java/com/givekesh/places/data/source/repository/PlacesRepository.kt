package com.givekesh.places.data.source.repository

import com.givekesh.places.data.model.local.CachedPlace
import com.givekesh.places.data.model.remote.FavoriteResponse
import com.givekesh.places.data.model.remote.PlacesResponse
import com.givekesh.places.data.source.local.PlacesDao
import com.givekesh.places.data.source.remote.NetworkApi
import javax.inject.Inject

class PlacesRepository @Inject constructor(
    private val networkApi: NetworkApi,
    private val placesDao: PlacesDao
) {
    suspend fun getPlaces(): PlacesResponse = networkApi.getPlaces()

    suspend fun getPromotedPlaces(): PlacesResponse = networkApi.getPromotedPlaces()

    suspend fun getFavorites(): FavoriteResponse = networkApi.getFavorites()

    suspend fun getCachedPlaces(): List<CachedPlace> = placesDao.getPlaces()

    suspend fun getFavoritePlaces(): List<CachedPlace> = placesDao.getFavorites()

    suspend fun insertCachedPlaces(data: List<CachedPlace>) {
        placesDao.insert(data)
    }
}