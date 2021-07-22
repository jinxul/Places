package com.givekesh.places.data.source.local

import androidx.room.*
import com.givekesh.places.data.model.local.CachedPlace

@Dao
interface PlacesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(data: List<CachedPlace>)

    @Query("SELECT * FROM Places WHERE title LIKE '%' || :searchQuery || '%' ORDER BY isPromoted DESC")
    suspend fun getPlaces(searchQuery: String): List<CachedPlace>

    @Query(
        "SELECT * FROM Places " +
                "WHERE isFavorite = 1 AND title LIKE '%' || :searchQuery || '%' " +
                "ORDER BY isPromoted DESC"
    )
    suspend fun getFavorites(searchQuery: String): List<CachedPlace>

    @Query("UPDATE Places SET isFavorite = :favorite WHERE id = :id")
    suspend fun setFavorites(id: Int, favorite: Boolean)
}