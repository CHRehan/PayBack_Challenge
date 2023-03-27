package com.payback.challenge.features.pixabay.domain.usecase

import android.content.res.Resources
import com.payback.challenge.R
import com.payback.challenge.features.common.Resource
import com.payback.challenge.features.common.Resource.Error
import com.payback.challenge.features.common.Resource.Success
import com.payback.challenge.features.pixabay.domain.model.ImageDetailsDomainModel
import com.payback.challenge.features.pixabay.domain.repository.ImagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ImagesUseCase @Inject constructor(
    private val imagesRepository: ImagesRepository,
    private val resources: Resources
) {
    operator fun invoke(query: String): Flow<Resource<List<ImageDetailsDomainModel>>> =
        flow {
            try {
                val images = imagesRepository.getImages(query)
                emit(Success(images))
            } catch (e: HttpException) {
                emit(Error(e.localizedMessage))
            } catch (e: IOException) {
                emit(Error(resources.getString(R.string.no_internet_message)))
            }
        }
}
