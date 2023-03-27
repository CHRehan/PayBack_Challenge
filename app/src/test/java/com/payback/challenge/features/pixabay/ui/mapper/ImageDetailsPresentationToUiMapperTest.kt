package com.payback.challenge.features.pixabay.ui.mapper

import com.payback.challenge.features.pixabay.presentation.model.ImageDetailsPresentationModel
import com.payback.challenge.features.pixabay.ui.model.ImageDetailsUiModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class ImageDetailsPresentationToUiMapperTest(
    private val input: ImageDetailsPresentationModel,
    private val expectedResult: ImageDetailsUiModel
) {

    companion object {
        private val input1 = ImageDetailsPresentationModel(
            id = 1,
            pageURL = "https://example.com",
            type = "photo",
            tags = "nature,landscape",
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
        private val expectedResult1 = ImageDetailsUiModel(
            id = 1,
            pageURL = "https://example.com",
            type = "photo",
            tags = listOf("nature", "landscape"),
            previewURL = "https://example.com/preview",
            webFormatURL = "https://example.com/webformat",
            largeImageURL = "https://example.com/large",
            downloads = "50",
            likes = "10",
            comments = "5",
            userId = 123,
            user = "johndoe",
            userImageURL = "https://example.com/user"
        )

        private val input2 = ImageDetailsPresentationModel(
            id = 2,
            pageURL = "https://example.com",
            type = "Scenery",
            tags = "nature,landscape",
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
        private val expectedResult2 = ImageDetailsUiModel(
            id = 2,
            pageURL = "https://example.com",
            type = "Scenery",
            tags = listOf("nature", "landscape"),
            previewURL = "https://example.com/preview",
            webFormatURL = "https://example.com/webformat",
            largeImageURL = "https://example.com/large",
            downloads = "50",
            likes = "10",
            comments = "5",
            userId = 123888,
            user = "johndoe",
            userImageURL = "https://example.com/user"
        )

        @JvmStatic
        @Parameters(name = "Given {0} When toUi Then returns {1}")
        fun params() = listOf(
            arrayOf(input1, expectedResult1),
            arrayOf(input2, expectedResult2)
        )
    }

    private lateinit var classUnderTest: ImageDetailsPresentationToUiMapper

    @Before
    fun setup() {
        classUnderTest = ImageDetailsPresentationToUiMapper()
    }

    @Test
    fun `Given presentation model When toUi then returns expected Ui model`() {
        // When
        val actualResult = classUnderTest.toUi(input)

        // Then
        assertEquals(expectedResult, actualResult)
    }
}
