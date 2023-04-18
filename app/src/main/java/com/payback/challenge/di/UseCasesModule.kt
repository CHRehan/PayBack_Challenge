package com.payback.challenge.di

import android.content.res.Resources
import com.payback.challenge.features.pixabay.domain.repository.ImagesRepository
import com.payback.challenge.features.pixabay.domain.usecase.ImagesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Provides
    fun provideImagesUseCase(imagesRepository: ImagesRepository, resources: Resources) =
        ImagesUseCase(imagesRepository, resources)
}
