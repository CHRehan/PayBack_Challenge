package com.payback.challenge.di

import android.content.Context
import com.payback.challenge.features.pixabay.data.datasource.local.ImageDetailsDao
import com.payback.challenge.storage.PixabayDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {
    @Provides
    fun providesPxDb(@ApplicationContext context: Context) = PixabayDb.create(context)

    @Provides
    fun providesPixabayDao(pixabayDb: PixabayDb): ImageDetailsDao = pixabayDb.PixabayDao()
}
