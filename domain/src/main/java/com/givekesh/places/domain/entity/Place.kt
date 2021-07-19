package com.givekesh.places.domain.entity

data class Place(
    val id: Int,
    val title: String,
    val shortAddress: String,
    val iconUrl: String,
    val bannerUrl: String,
    val description: String,
    val score: Double,
    val isPromoted: Boolean,
    var isFavorite: Boolean
)
