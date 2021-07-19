package com.givekesh.places.data.model.remote


import com.google.gson.annotations.SerializedName

data class FavoriteResponse(
    @SerializedName("favoriteIds")
    val favoriteIds: List<Int>
)