package com.givekesh.places.data.source.remote

import com.givekesh.places.data.model.remote.FavoriteResponse
import com.givekesh.places.data.model.remote.PlacesQualifier
import com.givekesh.places.data.model.remote.PlacesResponse
import retrofit2.http.GET

interface NetworkApi {
    @GET("places")
    @PlacesQualifier
    suspend fun getPlaces(): PlacesResponse

    @GET("promoted")
    @PlacesQualifier
    suspend fun getPromotedPlaces(): PlacesResponse

    @GET("favorites")
    suspend fun getFavorites(): FavoriteResponse
}