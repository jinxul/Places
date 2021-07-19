package com.givekesh.places.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.givekesh.places.data.model.local.CachedPlace

@Dao
interface PlacesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(data: List<CachedPlace>)

    @Query("SELECT * FROM Places")
    suspend fun getPlaces(): List<CachedPlace>

    @Query("SELECT * FROM Places WHERE isFavorite = 1")
    suspend fun getFavorites(): List<CachedPlace>
}