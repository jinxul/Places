package com.givekesh.places.data.di.module

import com.givekesh.places.data.source.repository.PlacesRepository
import com.givekesh.places.data.source.repository.PlacesRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindPlacesRepository(
        placesRepositoryImp: PlacesRepositoryImp
    ): PlacesRepository
}