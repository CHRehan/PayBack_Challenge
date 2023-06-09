package com.payback.challenge.features.pixabay.data.datasource.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_details")
data class ImageDetailsEntity(
    @PrimaryKey
    val id: Long,
    val searchQuery: String,
    val pageURL: String,
    val type: String,
    val tags: String,
    val previewURL: String,
    val webFormatURL: String,
    val largeImageURL: String,
    val downloads: Long,
    val likes: Long,
    val comments: Long,
    val userId: Long,
    val user: String,
    val userImageURL: String,
    val createdAt: Long
)
