package com.payback.challenge.features.pixabay.presentation.model

import com.payback.challenge.features.pixabay.presentation.ViewState

data class ImagesViewState(
    val isDataLoading: Boolean,
    val images: List<ImageDetailsPresentationModel>,
    val errorMessage: String
) : ViewState

fun emptyViewState() =
    ImagesViewState(isDataLoading = false, images = emptyList(), errorMessage = "")
