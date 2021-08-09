package com.givekesh.places.domain.mapper

import com.givekesh.places.data.model.local.CachedPlace
import com.givekesh.places.domain.entity.Place

interface PlaceMapper {
    fun mapToEntity(model: CachedPlace): Place

    fun mapToEntityList(models: List<CachedPlace>): List<Place>
}