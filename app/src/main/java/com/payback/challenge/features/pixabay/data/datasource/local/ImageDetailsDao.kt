package com.payback.challenge.features.pixabay.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.payback.challenge.features.pixabay.data.datasource.local.entities.ImageDetailsEntity

@Dao
interface ImageDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(countries: List<ImageDetailsEntity>)

    @Query("SELECT * FROM image_details WHERE searchQuery=:query")
    suspend fun getAllImageDetails(query: String): List<ImageDetailsEntity>?

    @Query("DELETE  FROM image_details WHERE searchQuery=:query")
    suspend fun deleteBySearchQuery(query: String)
}
