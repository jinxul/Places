package com.givekesh.places.data.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlaceResponse(
    @Json(name = "bannerUrl") val bannerUrl: String,
    @Json(name = "description") val description: String,
    @Json(name = "iconUrl") val iconUrl: String,
    @Json(name = "id") val id: Int,
    @Json(name = "score") val score: Double,
    @Json(name = "shortAddress") val shortAddress: String,
    @Json(name = "title") val title: String
)