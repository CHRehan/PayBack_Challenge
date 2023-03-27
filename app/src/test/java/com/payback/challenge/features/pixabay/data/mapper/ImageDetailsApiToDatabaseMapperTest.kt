package com.payback.challenge.features.pixabay.data.mapper

import com.payback.challenge.features.pixabay.data.datasource.local.entities.ImageDetailsEntity
import com.payback.challenge.features.pixabay.data.model.api.ImageDetailsApiModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class ImageDetailsApiToDatabaseMapperTest(
    private val input: MapperInput,
    private val expectedResult: ImageDetailsEntity
) {

    companion object {
        private const val searchQuery1 = "fruits"
        private const val searchQuery2 = "Scenery"
        private val systemCurrentTimeInSeconds = System.currentTimeMillis() / 1000L

        private val input1 = ImageDetailsApiModel(
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
        private val expectedResult1 = ImageDetailsEntity(
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
            searchQuery = searchQuery1,
            createdAt = systemCurrentTimeInSeconds
        )

        private val input2 = ImageDetailsApiModel(
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
        private val expectedResult2 = ImageDetailsEntity(
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
            searchQuery = searchQuery2,
            createdAt = systemCurrentTimeInSeconds
        )

        @JvmStatic
        @Parameters(name = "Given {0} When toDatabase Then returns {1}")
        fun params() = listOf(
            arrayOf(MapperInput(input1, searchQuery1, systemCurrentTimeInSeconds), expectedResult1),
            arrayOf(MapperInput(input2, searchQuery2, systemCurrentTimeInSeconds), expectedResult2)
        )
    }

    private lateinit var classUnderTest: ImageDetailsApiToDatabaseMapper

    @Before
    fun setup() {
        classUnderTest = ImageDetailsApiToDatabaseMapper()
    }

    @Test
    fun `Given database model When toDatabase then returns expected domain model`() {
        // When
        val actualResult = classUnderTest.toDatabase(input)

        // Then
        assertEquals(expectedResult, actualResult)
    }
}
