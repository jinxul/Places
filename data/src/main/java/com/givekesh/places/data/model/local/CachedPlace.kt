package com.givekesh.places.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Places")
data class CachedPlace(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val shortAddress: String,
    val iconUrl: String,
    val bannerUrl: String,
    val description: String,
    val score: Double,
    val isPromoted: Boolean = false,
    val isFavorite: Boolean = false
)
