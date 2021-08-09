package com.givekesh.places.data.source.repository

import android.content.SharedPreferences
import com.givekesh.places.data.model.local.CachedPlace
import com.givekesh.places.data.model.remote.FavoriteResponse
import com.givekesh.places.data.model.remote.PlacesResponse
import com.givekesh.places.data.source.local.PlacesDao
import com.givekesh.places.data.source.remote.NetworkApi
import javax.inject.Inject

class PlacesRepositoryImp @Inject constructor(
    private val networkApi: NetworkApi,
    private val placesDao: PlacesDao,
    private val preference: SharedPreferences
) : PlacesRepository {
    override suspend fun getPlaces(): PlacesResponse = networkApi.getPlaces()

    override suspend fun getPromotedPlaces(): PlacesResponse = networkApi.getPromotedPlaces()

    override suspend fun getFavorites(): FavoriteResponse = networkApi.getFavorites()

    override suspend fun getCachedPlaces(searchQuery: String): List<CachedPlace> =
        placesDao.getPlaces(searchQuery)

    override suspend fun getCachedFavoritePlaces(searchQuery: String): List<CachedPlace> =
        placesDao.getFavorites(searchQuery)

    override suspend fun setCachedFavoritePlaces(id: Int, isFavorite: Boolean) {
        placesDao.setFavorites(id, isFavorite)
    }

    override suspend fun insertCachedPlaces(data: List<CachedPlace>) {
        placesDao.insert(data)
    }

    override suspend fun getCachedFavoriteIds(): List<Int> = placesDao.getFavoriteIds()

    override fun isInitialSetup(): Boolean = preference.getBoolean("isInitialSetup", true)

    override fun finishInitialSetup() = preference.edit().putBoolean(
        "isInitialSetup", false
    ).apply()
}