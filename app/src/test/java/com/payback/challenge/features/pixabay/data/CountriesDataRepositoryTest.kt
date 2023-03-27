package com.payback.challenge.features.pixabay.data

import com.nhaarman.mockitokotlin2.given
import com.payback.challenge.features.pixabay.data.datasource.local.ImageDetailsDao
import com.payback.challenge.features.pixabay.data.datasource.local.entities.ImageDetailsEntity
import com.payback.challenge.features.pixabay.data.datasource.remote.PixabayAPI
import com.payback.challenge.features.pixabay.data.mapper.ImageDetailsApiToDatabaseMapper
import com.payback.challenge.features.pixabay.data.mapper.ImageDetailsApiToDomainMapper
import com.payback.challenge.features.pixabay.data.mapper.ImageDetailsDatabaseToDomainMapper
import com.payback.challenge.features.pixabay.data.mapper.MapperInput
import com.payback.challenge.features.pixabay.data.model.api.ImageDetailsApiModel
import com.payback.challenge.features.pixabay.data.model.api.ImagesApiModel
import com.payback.challenge.features.pixabay.data.repository.ImagesDataRepository
import com.payback.challenge.features.pixabay.domain.model.ImageDetailsDomainModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CountriesDataRepositoryTest {

    private val searchQuery = "fruits"
    private val systemCurrentTimeInSeconds = System.currentTimeMillis() / 1000L

    private val imageDetailsApi = ImageDetailsApiModel(
        id = 1,
        pageURL = "https://example.com",
        type = "photo",
        tags = "nature, landscape",
        previewURL = "https://example.com/preview",
        webFormatURL = "https://example.com/webformat",
        largeImageURL = "https://example.com/large",
        downloads = 50,
        likes = 10,
        comments = 5,
        userId = 123,
        user = "johndoe",
        userImageURL = "https://example.com/user"
    )
    private val imageDetailsDomain = ImageDetailsDomainModel(
        id = 1,
        pageURL = "https://example.com",
        type = "photo",
        tags = "nature, landscape",
        previewURL = "https://example.com/preview",
        webFormatURL = "https://example.com/webformat",
        largeImageURL = "https://example.com/large",
        downloads = 50,
        likes = 10,
        comments = 5,
        userId = 123,
        user = "johndoe",
        userImageURL = "https://example.com/user"
    )

    private val imageDetailsEntity = ImageDetailsEntity(
        id = 1,
        pageURL = "https://example.com",
        type = "photo",
        tags = "nature, landscape",
        previewURL = "https://example.com/preview",
        webFormatURL = "https://example.com/webformat",
        largeImageURL = "https://example.com/large",
        downloads = 50,
        likes = 10,
        comments = 5,
        userId = 123,
        user = "johndoe",
        userImageURL = "https://example.com/user",
        searchQuery = "fruits",
        createdAt = systemCurrentTimeInSeconds
    )

    @Mock
    lateinit var pixabayAPI: PixabayAPI

    @Mock
    lateinit var imageDetailsDao: ImageDetailsDao

    @Mock
    lateinit var imageDetailsApiToDomainMapper: ImageDetailsApiToDomainMapper

    @Mock
    lateinit var imageDetailsApiToDatabaseMapper: ImageDetailsApiToDatabaseMapper

    @Mock
    lateinit var imageDetailsDatabaseToDomainMapper: ImageDetailsDatabaseToDomainMapper

    private lateinit var classUnderTest: ImagesDataRepository

    @Before
    fun setup() {
        classUnderTest = ImagesDataRepository(
            pixabayAPI = pixabayAPI,
            imageDetailsDao = imageDetailsDao,
            imageDetailsDatabaseToDomainMapper = imageDetailsDatabaseToDomainMapper,
            imageDetailsApiToDatabaseMapper = imageDetailsApiToDatabaseMapper,
            imageDetailsApiToDomainMapper = imageDetailsApiToDomainMapper
        )
    }

    @Test
    fun `When getImages called Then return listOf ImageDetailsDomainModel From Local DB`() {
        runBlocking {
            // Given
            val expectedResult = listOf(imageDetailsDomain)

            given(imageDetailsDao.getAllImageDetails(searchQuery))
                .willReturn(listOf(imageDetailsEntity))

            given(imageDetailsDatabaseToDomainMapper.toDomain(imageDetailsEntity))
                .willReturn(imageDetailsDomain)

            // When
            val actualResult = classUnderTest.getImages(searchQuery)

            // Then
            assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun `When getImages called Then return listOf ImageDetailsDomainModel From RemoteSource`() {
        runBlocking {
            // Given
            val expectedResult = listOf(imageDetailsDomain)

            given(imageDetailsDao.getAllImageDetails(searchQuery))
                .willReturn(emptyList())

            given(pixabayAPI.getAllImages(query = searchQuery))
                .willReturn(ImagesApiModel(images = listOf(imageDetailsApi)))

            given(imageDetailsApiToDomainMapper.toDomain(imageDetailsApi))
                .willReturn(imageDetailsDomain)

            given(
                imageDetailsApiToDatabaseMapper.toDatabase(
                    MapperInput(
                        apiImageDetails = imageDetailsApi,
                        searchQuery = searchQuery,
                        systemCurrentTimeInSeconds
                    )
                )
            ).willReturn(imageDetailsEntity)

            // When
            val actualResult = classUnderTest.getImages(searchQuery)

            // Then
            assertEquals(expectedResult, actualResult)
        }
    }
}
