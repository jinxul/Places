package com.givekesh.places.data.source.repository

import android.content.SharedPreferences
import com.givekesh.places.data.model.local.CachedPlace
import com.givekesh.places.data.model.remote.FavoriteResponse
import com.givekesh.places.data.model.remote.PlacesResponse
import com.givekesh.places.data.source.local.PlacesDao
import com.givekesh.places.data.source.remote.NetworkApi
import javax.inject.Inject

class PlacesRepository @Inject constructor(
    private val networkApi: NetworkApi,
    private val placesDao: PlacesDao,
    private val preference: SharedPreferences
) {
    suspend fun getPlaces(): PlacesResponse = networkApi.getPlaces()

    suspend fun getPromotedPlaces(): PlacesResponse = networkApi.getPromotedPlaces()

    suspend fun getFavorites(): FavoriteResponse = networkApi.getFavorites()

    suspend fun getCachedPlaces(searchQuery: String = ""): List<CachedPlace> =
        placesDao.getPlaces(searchQuery)

    suspend fun getCachedFavoritePlaces(searchQuery: String = ""): List<CachedPlace> =
        placesDao.getFavorites(searchQuery)

    suspend fun setCachedFavoritePlaces(id: Int, isFavorite: Boolean) {
        placesDao.setFavorites(id, isFavorite)
    }

    suspend fun insertCachedPlaces(data: List<CachedPlace>) {
        placesDao.insert(data)
    }

    suspend fun getCachedFavoriteIds(): List<Int> = placesDao.getFavoriteIds()

    fun isInitialSetup(): Boolean = preference.getBoolean("isInitialSetup", true)

    fun finishInitialSetup() = preference.edit().putBoolean("isInitialSetup", false).commit()
}