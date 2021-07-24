package com.givekesh.places.data.di.module

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.givekesh.places.data.source.local.PlacesDao
import com.givekesh.places.data.source.remote.NetworkApi
import com.givekesh.places.data.source.repository.PlacesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun providePlacesRepository(
        networkApi: NetworkApi,
        placesDao: PlacesDao,
        preference: SharedPreferences
    ) = PlacesRepository(networkApi, placesDao, preference)

    @Singleton
    @Provides
    fun provideDefaultSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
}