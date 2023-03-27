package com.payback.challenge.features.pixabay.data.mapper

import com.payback.challenge.features.common.DatabaseToDomainMapper
import com.payback.challenge.features.pixabay.data.datasource.local.entities.ImageDetailsEntity
import com.payback.challenge.features.pixabay.domain.model.ImageDetailsDomainModel

class ImageDetailsDatabaseToDomainMapper :
    DatabaseToDomainMapper<ImageDetailsEntity, ImageDetailsDomainModel>() {

    public override fun map(input: ImageDetailsEntity) = ImageDetailsDomainModel(
        id = input.id,
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
