package com.payback.challenge.features.pixabay.presentation.mapper

import com.payback.challenge.features.common.DomainToPresentationMapper
import com.payback.challenge.features.common.orZero
import com.payback.challenge.features.pixabay.domain.model.ImageDetailsDomainModel
import com.payback.challenge.features.pixabay.presentation.model.ImageDetailsPresentationModel

class ImageDetailsDomainToPresentationMapper :
    DomainToPresentationMapper<ImageDetailsDomainModel, ImageDetailsPresentationModel>() {
    public override fun map(input: ImageDetailsDomainModel) = ImageDetailsPresentationModel(
        id = input.id.orZero(),
        pageURL = input.pageURL,
        type = input.type,
        tags = input.tags,
        previewURL = input.previewURL,
        webFormatURL = input.webFormatURL,
        largeImageURL = input.largeImageURL,
        downloads = input.downloads,
        likes = input.likes,
        comments = input.comments,
        userId = input.userId,
        user = input.user,
        userImageURL = input.userImageURL
    )
}
