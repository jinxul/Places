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

    suspend fun getCachedPlaces(searchQuery: String = ""): List<CachedPlace> =
        placesDao.getPlaces(searchQuery)

    suspend fun getFavoritePlaces(searchQuery: String = ""): List<CachedPlace> =
        placesDao.getFavorites(searchQuery)

    suspend fun setFavoritePlaces(id: Int, isFavorite: Boolean) {
        placesDao.setFavorites(id, isFavorite)
    }

    suspend fun insertCachedPlaces(data: List<CachedPlace>) {
        placesDao.insert(data)
    }

}