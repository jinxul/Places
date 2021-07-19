package com.givekesh.places.data.source.repository

import com.givekesh.places.data.model.remote.FavoriteResponse
import com.givekesh.places.data.model.remote.PlacesResponse
import com.givekesh.places.data.source.remote.NetworkApi
import javax.inject.Inject

class PlacesRepository @Inject constructor(
    private val networkApi: NetworkApi
) {
    suspend fun getPlaces(): PlacesResponse = networkApi.getPlaces()

    suspend fun getPromotedPlaces(): PlacesResponse = networkApi.getPromotedPlaces()

    suspend fun getFavorites(): FavoriteResponse = networkApi.getFavorites()
}