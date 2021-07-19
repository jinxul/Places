package com.givekesh.places.domain.di.modules

import com.givekesh.places.data.source.repository.PlacesRepository
import com.givekesh.places.domain.mapper.CachedPlaceMapper
import com.givekesh.places.domain.mapper.PlaceMapper
import com.givekesh.places.domain.usecase.FavoritesUseCase
import com.givekesh.places.domain.usecase.PlacesUseCase
import com.givekesh.places.domain.usecase.SearchUseCase
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

    @Singleton
    @Provides
    fun provideFavoritesUseCase(
        placesRepository: PlacesRepository,
        mapper: PlaceMapper
    ) = FavoritesUseCase(placesRepository, mapper)

    @Singleton
    @Provides
    fun provideSearchUseCase(
        placesRepository: PlacesRepository,
        mapper: PlaceMapper
    ) = SearchUseCase(placesRepository, mapper)
}