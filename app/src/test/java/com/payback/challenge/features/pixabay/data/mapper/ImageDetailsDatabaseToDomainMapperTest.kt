package com.payback.challenge.features.pixabay.data.mapper

import com.payback.challenge.features.pixabay.data.datasource.local.entities.ImageDetailsEntity
import com.payback.challenge.features.pixabay.domain.model.ImageDetailsDomainModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class ImageDetailsDatabaseToDomainMapperTest(
    private val input: ImageDetailsEntity,
    private val expectedResult: ImageDetailsDomainModel
) {

    companion object {
        private val systemCurrentTimeInSeconds = System.currentTimeMillis() / 1000L

        private val input1 = ImageDetailsEntity(
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
        private val expectedResult1 = ImageDetailsDomainModel(
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

        private val input2 = ImageDetailsEntity(
            id = 2,
            pageURL = "https://example.com",
            type = "Scenery",
            tags = "nature, landscape",
            previewURL = "https://example.com/preview",
            webFormatURL = "https://example.com/webformat",
            largeImageURL = "https://example.com/large",
            downloads = 50,
            likes = 10,
            comments = 5,
            userId = 123888,
            user = "johndoe",
            userImageURL = "https://example.com/user",
            searchQuery = "Scenery",
            createdAt = systemCurrentTimeInSeconds
        )
        private val expectedResult2 = ImageDetailsDomainModel(
            id = 2,
            pageURL = "https://example.com",
            type = "Scenery",
            tags = "nature, landscape",
            previewURL = "https://example.com/preview",
            webFormatURL = "https://example.com/webformat",
            largeImageURL = "https://example.com/large",
            downloads = 50,
            likes = 10,
            comments = 5,
            userId = 123888,
            user = "johndoe",
            userImageURL = "https://example.com/user"
        )

        @JvmStatic
        @Parameters(name = "Given {0} When toDomain Then returns {1}")
        fun params() = listOf(
            arrayOf(input1, expectedResult1),
            arrayOf(input2, expectedResult2)
        )
    }

    private lateinit var classUnderTest: ImageDetailsDatabaseToDomainMapper

    @Before
    fun setup() {
        classUnderTest = ImageDetailsDatabaseToDomainMapper()
    }

    @Test
    fun `Given database model When toDomain then returns expected domain model`() {
        // When
        val actualResult = classUnderTest.toDomain(input)

        // Then
        assertEquals(expectedResult, actualResult)
    }
}
