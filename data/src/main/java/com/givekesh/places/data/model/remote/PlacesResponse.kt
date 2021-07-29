package com.givekesh.places.data.model.remote

import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
data class PlacesResponse(
    @PlacesQualifier val placeResponses: List<PlaceResponse>
)

@JsonClass(generateAdapter = true)
data class PlacesIntermediate(
    @Json(name = "places") val places: List<PlaceResponse>?,
    @Json(name = "promotedPlaces") val promotedPlaces: List<PlaceResponse>?
)

object PlacesJsonAdapter {
    @FromJson
    @PlacesQualifier
    fun fomJson(
        intermediate: PlacesIntermediate
    ): PlacesResponse = PlacesResponse(
        placeResponses = intermediate.places ?: intermediate.promotedPlaces ?: listOf()
    )

    @ToJson
    fun toJson(
        @PlacesQualifier placesResponse: PlacesResponse
    ): PlacesIntermediate = PlacesIntermediate(
        places = placesResponse.placeResponses,
        promotedPlaces = null
    )
}

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class PlacesQualifier