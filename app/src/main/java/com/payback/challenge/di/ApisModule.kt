package com.payback.challenge.di

import com.payback.challenge.features.pixabay.data.datasource.remote.PixabayAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApisModule {

    @Singleton
    @Provides
    fun provideCountriesApi(retrofit: Retrofit): PixabayAPI =
        retrofit.create(PixabayAPI::class.java)
}
