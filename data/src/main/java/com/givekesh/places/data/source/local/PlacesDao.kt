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

    @Query("SELECT * FROM Places WHERE title LIKE '%' || :searchQuery || '%'")
    suspend fun getPlaces(searchQuery: String): List<CachedPlace>

    @Query("SELECT * FROM Places WHERE isFavorite = 1 AND title LIKE '%' || :searchQuery || '%'")
    suspend fun getFavorites(searchQuery: String): List<CachedPlace>
}