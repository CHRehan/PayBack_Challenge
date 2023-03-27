package com.payback.challenge.di

import com.payback.challenge.features.pixabay.data.mapper.ImageDetailsApiToDatabaseMapper
import com.payback.challenge.features.pixabay.data.mapper.ImageDetailsApiToDomainMapper
import com.payback.challenge.features.pixabay.data.mapper.ImageDetailsDatabaseToDomainMapper
import com.payback.challenge.features.pixabay.presentation.mapper.ImageDetailsDomainToPresentationMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MappersModule {
    @Singleton
    @Provides
    fun provideImageDetailsApiToDomainMapper(): ImageDetailsApiToDomainMapper =
        ImageDetailsApiToDomainMapper()

    @Singleton
    @Provides
    fun provideImageDetailsDomainToPresentationMapper(): ImageDetailsDomainToPresentationMapper =
        ImageDetailsDomainToPresentationMapper()

    @Singleton
    @Provides
    fun provideCountriesDatabaseToDomainMapper(): ImageDetailsDatabaseToDomainMapper =
        ImageDetailsDatabaseToDomainMapper()

    @Singleton
    @Provides
    fun provideCountriesDataToDatabaseMapper(): ImageDetailsApiToDatabaseMapper =
        ImageDetailsApiToDatabaseMapper()
}
