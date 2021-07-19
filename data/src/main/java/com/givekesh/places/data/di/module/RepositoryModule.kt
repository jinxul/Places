package com.givekesh.places.data.di.module

import com.givekesh.places.data.source.local.PlacesDao
import com.givekesh.places.data.source.remote.NetworkApi
import com.givekesh.places.data.source.repository.PlacesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun providePlacesRepository(
        networkApi: NetworkApi, placesDao: PlacesDao
    ) = PlacesRepository(networkApi, placesDao)
}