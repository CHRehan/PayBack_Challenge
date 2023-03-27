package com.payback.challenge.features.pixabay.domain.repository

import com.payback.challenge.features.pixabay.domain.model.ImageDetailsDomainModel

interface ImagesRepository {
    suspend fun getImages(query: String): List<ImageDetailsDomainModel>
}
