package com.payback.challenge.features.pixabay.data.mapper

import com.payback.challenge.features.common.ApiToDatabaseMapper
import com.payback.challenge.features.common.orZero
import com.payback.challenge.features.pixabay.data.datasource.local.entities.ImageDetailsEntity
import com.payback.challenge.features.pixabay.data.model.api.ImageDetailsApiModel

class ImageDetailsApiToDatabaseMapper :
    ApiToDatabaseMapper<MapperInput, ImageDetailsEntity>() {
    override fun map(input: MapperInput): ImageDetailsEntity {
        val imageDetail = input.apiImageDetails
        return ImageDetailsEntity(
            id = imageDetail.id.orZero(),
            pageURL = imageDetail.pageURL.orEmpty(),
            type = imageDetail.type.orEmpty(),
            tags = imageDetail.tags.orEmpty(),
            previewURL = imageDetail.previewURL.orEmpty(),
            webFormatURL = imageDetail.webFormatURL.orEmpty(),
            largeImageURL = imageDetail.largeImageURL.orEmpty(),
            downloads = imageDetail.downloads.orZero(),
            likes = imageDetail.likes.orZero(),
            comments = imageDetail.comments.orZero(),
            userId = imageDetail.userId.orZero(),
            user = imageDetail.user.orEmpty(),
            userImageURL = imageDetail.userImageURL.orEmpty(),
            searchQuery = input.searchQuery,
            createdAt = input.createdAt
        )
    }
}

data class MapperInput(
    val apiImageDetails: ImageDetailsApiModel,
    val searchQuery: String,
    val createdAt: Long
)
