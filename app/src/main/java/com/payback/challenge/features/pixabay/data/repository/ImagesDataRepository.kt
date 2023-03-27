package com.payback.challenge.features.pixabay.data.repository

import com.payback.challenge.features.pixabay.data.datasource.remote.PixabayAPI
import com.payback.challenge.features.pixabay.data.mapper.ImageDetailsApiToDatabaseMapper
import com.payback.challenge.features.pixabay.data.mapper.ImageDetailsApiToDomainMapper
import com.payback.challenge.features.pixabay.data.mapper.ImageDetailsDatabaseToDomainMapper
import com.payback.challenge.features.pixabay.data.mapper.MapperInput
import com.payback.challenge.features.pixabay.data.datasource.local.ImageDetailsDao
import com.payback.challenge.features.pixabay.domain.model.ImageDetailsDomainModel
import com.payback.challenge.features.pixabay.domain.repository.ImagesRepository
import javax.inject.Inject

private const val FETCH_INTERVAL_IN_SECONDS: Int = (24 * 60 * 60)

class ImagesDataRepository @Inject constructor(
    private val pixabayAPI: PixabayAPI,
    private val imageDetailsDao: ImageDetailsDao,
    private val imageDetailsApiToDomainMapper: ImageDetailsApiToDomainMapper,
    private val imageDetailsApiToDatabaseMapper: ImageDetailsApiToDatabaseMapper,
    private val imageDetailsDatabaseToDomainMapper: ImageDetailsDatabaseToDomainMapper
) : ImagesRepository {
    override suspend fun getImages(query: String): List<ImageDetailsDomainModel> {
        val localData = imageDetailsDao.getAllImageDetails(query)
        return when {
            localData.isNullOrEmpty() -> {
                fetchAndPersistData(query)
            }
            isRefreshNeeded(localData[0].createdAt) -> {
                imageDetailsDao.deleteBySearchQuery(query)
                fetchAndPersistData(query)
            }
            else -> {
                localData.map { data -> imageDetailsDatabaseToDomainMapper.toDomain(data) }
            }
        }
    }

    private suspend fun fetchAndPersistData(query: String): List<ImageDetailsDomainModel> {
        val imagesApiResponse = pixabayAPI.getAllImages(query = query)
        imagesApiResponse.images?.map { apiImageDetail ->
            imageDetailsApiToDatabaseMapper.toDatabase(
                MapperInput(apiImageDetail, query, getCurrentTimeInSeconds())
            )
        }?.let { images -> imageDetailsDao.insertAll(images) }

        return imagesApiResponse.images?.map { imageDetail ->
            imageDetailsApiToDomainMapper.toDomain(imageDetail)
        } ?: emptyList()
    }

    private fun getCurrentTimeInSeconds() = System.currentTimeMillis() / 1000L

    private fun isRefreshNeeded(lastFetchTimeInSeconds: Long): Boolean {
        return getCurrentTimeInSeconds() - lastFetchTimeInSeconds >= FETCH_INTERVAL_IN_SECONDS
    }
}
