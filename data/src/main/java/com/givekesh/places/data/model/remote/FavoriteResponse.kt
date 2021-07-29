package com.givekesh.places.data.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FavoriteResponse(
    @Json(name = "favoriteIds") val favoriteIds: List<Int>
)