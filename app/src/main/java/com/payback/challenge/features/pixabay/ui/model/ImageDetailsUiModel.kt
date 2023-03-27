package com.payback.challenge.features.pixabay.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageDetailsUiModel(
    val id: Long,
    val pageURL: String,
    val type: String,
    val tags: List<String>,
    val previewURL: String,
    val webFormatURL: String,
    val largeImageURL: String,
    val downloads: String,
    val likes: String,
    val comments: String,
    val userId: Long,
    val user: String,
    val userImageURL: String
) : Parcelable
