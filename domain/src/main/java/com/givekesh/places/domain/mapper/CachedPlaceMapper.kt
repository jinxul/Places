package com.givekesh.places.domain.mapper

import com.givekesh.places.data.model.local.CachedPlace
import com.givekesh.places.data.model.remote.PlaceResponse

interface CachedPlaceMapper {
    fun mapToEntity(
        model: PlaceResponse,
        isPromoted: Boolean,
        isFavorite: Boolean
    ): CachedPlace

    fun mapToEntityList(
        models: List<PlaceResponse>,
        favorites: List<Int>,
        isPromoted: Boolean
    ): List<CachedPlace>
}