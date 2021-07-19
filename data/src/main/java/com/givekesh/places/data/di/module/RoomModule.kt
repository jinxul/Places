package com.givekesh.places.data.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.givekesh.places.data.source.local.MainDatabase
import com.givekesh.places.data.source.local.PlacesDao
import com.givekesh.places.data.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideDatabaseBuilder(
        @ApplicationContext context: Context
    ): RoomDatabase.Builder<MainDatabase> = Room.databaseBuilder(
        context,
        MainDatabase::class.java,
        Constants.DATABASE_NAME
    )

    @Singleton
    @Provides
    fun provideMainDatabase(
        databaseBuilder: RoomDatabase.Builder<MainDatabase>
    ): MainDatabase = databaseBuilder.fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun providePlacesDao(db: MainDatabase): PlacesDao = db.placesDao()
}