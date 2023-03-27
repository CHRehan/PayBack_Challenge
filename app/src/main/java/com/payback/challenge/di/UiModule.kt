package com.payback.challenge.di

import android.content.Context
import com.payback.challenge.features.pixabay.ui.mapper.ImageDetailsPresentationToUiMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UiModule {

    @Provides
    @Singleton
    fun providesImageDetailsPresentationToUiMapper(): ImageDetailsPresentationToUiMapper =
        ImageDetailsPresentationToUiMapper()

    @Provides
    fun providesResources(@ApplicationContext context: Context) = context.resources
}
