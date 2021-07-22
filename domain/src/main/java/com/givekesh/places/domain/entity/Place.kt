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
) : Parcelable {
    override fun equals(other: Any?): Boolean = other is Place &&
            id == other.id &&
            title == other.title &&
            shortAddress == other.shortAddress &&
            iconUrl == other.iconUrl &&
            bannerUrl == other.bannerUrl &&
            description == other.description &&
            score == other.score

    override fun hashCode(): Int {
        var result = id
        result *= 31 + title.hashCode()
        result *= 31 + shortAddress.hashCode()
        result *= 31 + iconUrl.hashCode()
        result *= 31 + bannerUrl.hashCode()
        result *= 31 + description.hashCode()
        result *= 31 + score.hashCode()
        return result
    }
}