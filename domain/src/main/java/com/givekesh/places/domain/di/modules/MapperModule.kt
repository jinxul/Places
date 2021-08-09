package com.givekesh.places.domain.di.modules

import com.givekesh.places.domain.mapper.CachedPlaceMapper
import com.givekesh.places.domain.mapper.CachedPlaceMapperImp
import com.givekesh.places.domain.mapper.PlaceMapper
import com.givekesh.places.domain.mapper.PlaceMapperImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {
    @Singleton
    @Binds
    abstract fun bindCachedPlaceMapper(
        cachedPlaceMapperImp: CachedPlaceMapperImp
    ): CachedPlaceMapper

    @Singleton
    @Binds
    abstract fun bindPlaceMapper(
        placeMapperImp: PlaceMapperImp
    ): PlaceMapper
}