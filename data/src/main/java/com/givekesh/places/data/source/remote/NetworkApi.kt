package com.givekesh.places.data.source.remote

import com.givekesh.places.data.model.remote.FavoriteResponse
import com.givekesh.places.data.model.remote.PlacesResponse
import retrofit2.http.GET

interface NetworkApi {
    @GET("places")
    suspend fun getPlaces(): PlacesResponse

    @GET("promoted")
    suspend fun getPromotedPlaces(): PlacesResponse

    @GET("favorites")
    suspend fun getFavorites(): FavoriteResponse
}