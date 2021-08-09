package com.givekesh.places.domain.di.modules

import com.givekesh.places.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Singleton
    @Binds
    abstract fun bindPlacesUseCase(
        placesUseCaseImp: PlacesUseCaseImp
    ): PlacesUseCase

    @Singleton
    @Binds
    abstract fun bindFavoritesUseCase(
        favoritesUseCaseImp: FavoritesUseCaseImp
    ): FavoritesUseCase

    @Singleton
    @Binds
    abstract fun bindSearchUseCase(
        searchUseCaseImp: SearchUseCaseImp
    ): SearchUseCase
}