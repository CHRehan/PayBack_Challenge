package com.payback.challenge.features.pixabay.domain

import android.content.res.Resources
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.whenever
import com.payback.challenge.features.pixabay.domain.model.ImageDetailsDomainModel
import com.payback.challenge.features.pixabay.domain.repository.ImagesRepository
import com.payback.challenge.features.pixabay.domain.usecase.ImagesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ImagesUseCaseTest {
    private val searchQuery = "fruits"
    private val imageDetailsDomain1 = ImageDetailsDomainModel(
        id = 1L,
        pageURL = "https://www.example.com",
        type = "photo",
        tags = "nature, outdoors",
        previewURL = "https://www.example.com/preview.jpg",
        webFormatURL = "https://www.example.com/web.jpg",
        largeImageURL = "https://www.example.com/large.jpg",
        downloads = 500L,
        likes = 200L,
        comments = 50L,
        userId = 5678L,
        user = "John Doe",
        userImageURL = "https://www.example.com/user.jpg"
    )

    private val imageDetailsDomain2 = ImageDetailsDomainModel(
        id = 2L,
        pageURL = "https://www.example.com",
        type = "photo",
        tags = "nature, outdoors",
        previewURL = "https://www.example.com/preview.jpg",
        webFormatURL = "https://www.example.com/web.jpg",
        largeImageURL = "https://www.example.com/large.jpg",
        downloads = 500L,
        likes = 200L,
        comments = 50L,
        userId = 5678L,
        user = "John Doe",
        userImageURL = "https://www.example.com/user.jpg"
    )

    @Mock
    lateinit var imagesRepository: ImagesRepository

    @Mock
    lateinit var resources: Resources

    private lateinit var classUnderTest: ImagesUseCase

    @Before
    fun setup() {
        classUnderTest = ImagesUseCase(imagesRepository, resources)
    }

    @Test
    fun `Given SearchQuery When executeInBackground Then FlowOf Resource of Collection ImageDetailsDomainModel is returned`() =
        runBlocking {
            // Given
            val images = listOf(
                imageDetailsDomain1,
                imageDetailsDomain2
            )
            given(imagesRepository.getImages(searchQuery)).willReturn(images)

            val expectedResult = listOf(imageDetailsDomain1, imageDetailsDomain2)
            var actualResult: List<ImageDetailsDomainModel>? = null
            classUnderTest(searchQuery).collect {
                actualResult = it.data
            }

            // Then
            assertEquals(expectedResult, actualResult)
        }

    @Test
    fun `Given SearchQuery When executeInBackground Then throws HttpException`() =
        runBlocking {
            // Given
            val message = "An unexpected error occured"
            val response = Response.error<String>(
                404,
                message.toResponseBody("text/plain".toMediaTypeOrNull())
            )

            whenever(imagesRepository.getImages(searchQuery)).thenThrow(HttpException(response))
            val expectedResult = "HTTP 404 " + response.message()
            // When
            var actualResult: String? = null
            classUnderTest(searchQuery).collect {
                actualResult = it.message
            }

            // Then
            assertEquals(expectedResult, actualResult)
        }
}
