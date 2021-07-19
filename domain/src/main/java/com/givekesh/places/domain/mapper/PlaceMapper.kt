package com.givekesh.places.domain.mapper

import com.givekesh.places.data.model.local.CachedPlace
import com.givekesh.places.domain.entity.Place
import javax.inject.Inject

class PlaceMapper @Inject constructor() {
    private fun mapToEntity(model: CachedPlace): Place = Place(
        id = model.id,
        title = model.title,
        shortAddress = model.shortAddress,
        iconUrl = model.iconUrl,
        bannerUrl = model.bannerUrl,
        description = model.description,
        score = model.score,
        isPromoted = model.isPromoted,
        isFavorite = model.isFavorite
    )

    fun mapToEntityList(models: List<CachedPlace>): List<Place> = models.map { mapToEntity(it) }
}