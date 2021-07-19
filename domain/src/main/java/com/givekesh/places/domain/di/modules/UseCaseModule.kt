package com.givekesh.places.domain.di.modules

import com.givekesh.places.data.source.repository.PlacesRepository
import com.givekesh.places.domain.mapper.CachedPlaceMapper
import com.givekesh.places.domain.mapper.PlaceMapper
import com.givekesh.places.domain.usecase.PlacesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun providePlacesUseCase(
        placesRepository: PlacesRepository,
        cachedMapper: CachedPlaceMapper,
        mapper: PlaceMapper
    ) = PlacesUseCase(placesRepository, cachedMapper, mapper)
}