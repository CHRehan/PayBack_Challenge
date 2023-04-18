package com.payback.challenge.di

import com.payback.challenge.features.pixabay.data.datasource.local.ImageDetailsDao
import com.payback.challenge.features.pixabay.data.datasource.remote.PixabayAPI
import com.payback.challenge.features.pixabay.data.mapper.ImageDetailsApiToDatabaseMapper
import com.payback.challenge.features.pixabay.data.mapper.ImageDetailsApiToDomainMapper
import com.payback.challenge.features.pixabay.data.mapper.ImageDetailsDatabaseToDomainMapper
import com.payback.challenge.features.pixabay.data.repository.ImagesDataRepository
import com.payback.challenge.features.pixabay.domain.repository.ImagesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {

    @Singleton
    @Provides
    fun provideImagesDataRepository(
        pixabayAPI: PixabayAPI,
        imageDetailsDao: ImageDetailsDao,
        imageDetailsApiToDomainMapper: ImageDetailsApiToDomainMapper,
        imageDetailsApiToDatabaseMapper: ImageDetailsApiToDatabaseMapper,
        imageDetailsDatabaseToDomainMapper: ImageDetailsDatabaseToDomainMapper
    ): ImagesRepository {
        return ImagesDataRepository(
            pixabayAPI,
            imageDetailsDao,
            imageDetailsApiToDomainMapper,
            imageDetailsApiToDatabaseMapper,
            imageDetailsDatabaseToDomainMapper
        )
    }
}
