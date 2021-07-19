package com.givekesh.places.data.model.remote


import com.google.gson.annotations.SerializedName

data class PlacesResponse(
    @SerializedName("places", alternate = ["promotedPlaces"])
    val placeResponses: List<PlaceResponse>
)