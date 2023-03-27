package com.payback.challenge.features.pixabay.data.model.api

import com.google.gson.annotations.SerializedName

data class ImageDetailsApiModel(
    val id: Long?,
    val pageURL: String?,
    val type: String?,
    val tags: String?,
    val previewURL: String?,
    val webFormatURL: String?,
    val largeImageURL: String?,
    val downloads: Long?,
    val likes: Long?,
    val comments: Long?,
    @SerializedName("user_id")
    val userId: Long?,
    @SerializedName("user")
    val user: String?,
    @SerializedName("userImageURL")
    val userImageURL: String?
)
