package com.givekesh.places.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.givekesh.places.data.model.local.CachedPlace

@Database(
    entities = [CachedPlace::class],
    version = 1,
    exportSchema = false
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun placesDao(): PlacesDao
}