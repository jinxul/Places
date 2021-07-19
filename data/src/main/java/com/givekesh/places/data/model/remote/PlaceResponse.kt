package com.givekesh.places.data.model.remote


import com.google.gson.annotations.SerializedName

data class PlaceResponse(
    @SerializedName("bannerUrl")
    val bannerUrl: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("iconUrl")
    val iconUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("score")
    val score: Double,
    @SerializedName("shortAddress")
    val shortAddress: String,
    @SerializedName("title")
    val title: String
)