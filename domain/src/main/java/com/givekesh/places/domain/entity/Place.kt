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
    override fun equals(other: Any?): Boolean {
        if (other !is Place)
            return false
        return (id == other.id &&
                title == other.title &&
                shortAddress == other.shortAddress &&
                iconUrl == other.iconUrl &&
                bannerUrl == other.bannerUrl &&
                description == other.description &&
                score == other.score
                )
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + shortAddress.hashCode()
        result = 31 * result + iconUrl.hashCode()
        result = 31 * result + bannerUrl.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + score.hashCode()
        result = 31 * result + isPromoted.hashCode()
        result = 31 * result + isFavorite.hashCode()
        return result
    }
}