package com.givekesh.places.domain.mapper

import com.givekesh.places.data.model.local.CachedPlace
import com.givekesh.places.data.model.remote.PlaceResponse
import javax.inject.Inject

class CachedPlaceMapperImp @Inject constructor() : CachedPlaceMapper {
    override fun mapToEntity(
        model: PlaceResponse,
        isPromoted: Boolean,
        isFavorite: Boolean
    ): CachedPlace = CachedPlace(
        id = model.id,
        title = model.title,
        shortAddress = model.shortAddress,
        iconUrl = model.iconUrl,
        bannerUrl = model.bannerUrl,
        description = model.description,
        score = model.score,
        isPromoted = isPromoted,
        isFavorite = isFavorite
    )

    override fun mapToEntityList(
        models: List<PlaceResponse>,
        favorites: List<Int>,
        isPromoted: Boolean
    ): List<CachedPlace> = models.map { mapToEntity(it, isPromoted, favorites.contains(it.id)) }
}