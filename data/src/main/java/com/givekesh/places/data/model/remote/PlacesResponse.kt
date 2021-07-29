package com.givekesh.places.data.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlacesResponse(
    @Json(name = "places")
    val placeResponses: List<PlaceResponse>
)

@JsonClass(generateAdapter = true)
data class PromotedResponse(
    @Json(name = "promotedPlaces")
    val placeResponses: List<PlaceResponse>
)