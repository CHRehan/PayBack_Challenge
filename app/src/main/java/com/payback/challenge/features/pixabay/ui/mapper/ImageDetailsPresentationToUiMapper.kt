package com.payback.challenge.features.pixabay.ui.mapper

import com.payback.challenge.features.common.PresentationToUiMapper
import com.payback.challenge.features.common.format
import com.payback.challenge.features.common.orZero
import com.payback.challenge.features.pixabay.presentation.model.ImageDetailsPresentationModel
import com.payback.challenge.features.pixabay.ui.model.ImageDetailsUiModel

class ImageDetailsPresentationToUiMapper :
    PresentationToUiMapper<ImageDetailsPresentationModel, ImageDetailsUiModel>() {
    override fun map(input: ImageDetailsPresentationModel) = ImageDetailsUiModel(
        id = input.id.orZero(),
        pageURL = input.pageURL,
        type = input.type,
        tags = input.tags.splitTags(),
        previewURL = input.previewURL,
        webFormatURL = input.webFormatURL,
        largeImageURL = input.largeImageURL,
        downloads = input.downloads.format(),
        likes = input.likes.format(),
        comments = input.comments.format(),
        userId = input.userId.orZero(),
        user = input.user,
        userImageURL = input.userImageURL
    )
}

private fun String.splitTags(): List<String> {
    return if (isEmpty()) emptyList()
    else if (contains(",")) {
        split(",")
    } else listOf(this)
}
