package com.payback.challenge.features.pixabay.data.datasource.remote

import com.payback.challenge.BuildConfig
import com.payback.challenge.features.pixabay.data.model.api.ImagesApiModel
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Locale

interface PixabayAPI {
    @GET("api/")
    suspend fun getAllImages(
        @Query("key") apiKey: String = BuildConfig.API_KEY,
        @Query("q") query: String,
        @Query("lang") language: String = Locale.getDefault().language
    ): ImagesApiModel
}
