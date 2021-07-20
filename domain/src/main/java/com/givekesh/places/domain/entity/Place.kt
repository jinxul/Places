package com.givekesh.places.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
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
) : Parcelable
