package com.payback.challenge.features.pixabay.presentation

import android.content.res.Resources
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.whenever
import com.payback.challenge.features.common.MainCoroutineRule
import com.payback.challenge.features.common.Resource
import com.payback.challenge.features.common.Resource.Success
import com.payback.challenge.features.pixabay.domain.model.ImageDetailsDomainModel
import com.payback.challenge.features.pixabay.domain.usecase.ImagesUseCase
import com.payback.challenge.features.pixabay.presentation.mapper.ImageDetailsDomainToPresentationMapper
import com.payback.challenge.features.pixabay.presentation.model.ImageDetailsPresentationModel
import com.payback.challenge.features.pixabay.presentation.model.ImagesViewState
import com.payback.challenge.features.pixabay.ui.mapper.ImageDetailsPresentationToUiMapper
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ImagesViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var imagesUseCase: ImagesUseCase

    @Mock
    lateinit var resource: Resources

    @Mock
    lateinit var imageDetailsDomainToPresentation: ImageDetailsDomainToPresentationMapper

    @Mock
    lateinit var imageDetailsToPresentationUiMapper: ImageDetailsPresentationToUiMapper

    private lateinit var classUnderTest: ImagesViewModel

    private val searchQuery = "fruits"
    private val imageDetailsDomain = ImageDetailsDomainModel(
        id = 1234L,
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

    private val imageDetailsPresentation = ImageDetailsPresentationModel(
        id = 123,
        pageURL = "https://www.example.com",
        type = "jpg",
        tags = "fruit, apple",
        previewURL = "https://www.example.com/preview.jpg",
        webFormatURL = "https://www.example.com/webformat.jpg",
        largeImageURL = "https://www.example.com/large.jpg",
        downloads = 5,
        likes = 3,
        comments = 1,
        userId = 456,
        user = "John Doe",
        userImageURL = "https://www.example.com/user.jpg"
    )

    @Before
    fun setUp() {
        classUnderTest = ImagesViewModel(
            imagesUseCase,
            imageDetailsDomainToPresentation,
            resource,
            imageDetailsToPresentationUiMapper
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Given searchQuery When getImages is called with a successful response, Then update view state with images`() =
        runTest {
            // Given
            val expectedImages = listOf(imageDetailsPresentation)
            val imagesFlow = flowOf(Success(listOf(imageDetailsDomain)))

            given(imagesUseCase(anyString())).willReturn(imagesFlow)
            given(imageDetailsDomainToPresentation.toPresentation(imageDetailsDomain))
                .willReturn(imageDetailsPresentation)

            // When
            classUnderTest.getImages(searchQuery)

            // Then
            val expectedViewState =
                ImagesViewState(isDataLoading = false, images = expectedImages, errorMessage = "")
            val actualViewState = classUnderTest.currentViewState()
            assertEquals(expectedViewState, actualViewState)
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `Given searchQuery When getImages is called with an error response, Then update view state with error message`() =
        runTest {
            // Given
            val errorMessage = "An error occurred"
            val imagesFlow = flowOf(Resource.Error<List<ImageDetailsDomainModel>>(errorMessage))
            whenever(imagesUseCase(anyString())).thenReturn(imagesFlow)

            // When
            classUnderTest.getImages(searchQuery)

            // Then
            val expectedViewState = ImagesViewState(
                errorMessage = errorMessage,
                isDataLoading = false,
                images = emptyList()
            )
            val actualViewState = classUnderTest.currentViewState()
            assertEquals(expectedViewState, actualViewState)
        }
}
