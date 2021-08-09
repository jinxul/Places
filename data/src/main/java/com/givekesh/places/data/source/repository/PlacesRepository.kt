package com.givekesh.places.data.source.repository

import com.givekesh.places.data.model.local.CachedPlace
import com.givekesh.places.data.model.remote.FavoriteResponse
import com.givekesh.places.data.model.remote.PlacesResponse

interface PlacesRepository {
    suspend fun getPlaces(): PlacesResponse

    suspend fun getPromotedPlaces(): PlacesResponse

    suspend fun getFavorites(): FavoriteResponse

    suspend fun getCachedPlaces(searchQuery: String = ""): List<CachedPlace>

    suspend fun getCachedFavoritePlaces(searchQuery: String = ""): List<CachedPlace>

    suspend fun setCachedFavoritePlaces(id: Int, isFavorite: Boolean)

    suspend fun insertCachedPlaces(data: List<CachedPlace>)

    suspend fun getCachedFavoriteIds(): List<Int>

    fun isInitialSetup(): Boolean

    fun finishInitialSetup()
}