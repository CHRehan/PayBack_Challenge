package com.payback.challenge.features.pixabay.data.mapper

import com.payback.challenge.features.common.ApiToDomainMapper
import com.payback.challenge.features.common.orZero
import com.payback.challenge.features.pixabay.data.model.api.ImageDetailsApiModel
import com.payback.challenge.features.pixabay.domain.model.ImageDetailsDomainModel

class ImageDetailsApiToDomainMapper :
    ApiToDomainMapper<ImageDetailsApiModel, ImageDetailsDomainModel>() {
    override fun map(input: ImageDetailsApiModel) = ImageDetailsDomainModel(
        id = input.id.orZero(),
        pageURL = input.pageURL.orEmpty(),
        type = input.type.orEmpty(),
        tags = input.tags.orEmpty(),
        previewURL = input.previewURL.orEmpty(),
        webFormatURL = input.webFormatURL.orEmpty(),
        largeImageURL = input.largeImageURL.orEmpty(),
        downloads = input.downloads.orZero(),
        likes = input.likes.orZero(),
        comments = input.comments.orZero(),
        userId = input.userId.orZero(),
        user = input.user.orEmpty(),
        userImageURL = input.userImageURL.orEmpty()
    )
}
