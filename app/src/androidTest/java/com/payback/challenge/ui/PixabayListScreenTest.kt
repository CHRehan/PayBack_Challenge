package com.payback.challenge.ui

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.payback.challenge.MainActivity
import com.payback.challenge.R
import com.payback.challenge.features.pixabay.ui.model.ImageDetailsUiModel
import com.payback.challenge.features.pixabay.ui.pixabaylist.ItemPixabayList
import com.payback.challenge.features.pixabay.ui.pixabaylist.PixabayList
import com.payback.challenge.features.pixabay.ui.theme.PaybackChallengeTheme
import com.payback.challenge.utils.TestTags.IMAGE_LIST
import com.payback.challenge.utils.TestTags.ITEM_IMAGE_LIST
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalLayoutApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@RunWith(AndroidJUnit4::class)
class PixabayListScreenTest {

    private val imageDetailsUi1 = ImageDetailsUiModel(
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

    private val imageDetailsUi2 = ImageDetailsUiModel(
        id = 2,
        pageURL = "https://example.com",
        type = "photo",
        tags = listOf("nature", "landscape"),
        previewURL = "https://example.com/preview",
        webFormatURL = "https://example.com/webformat",
        largeImageURL = "https://example.com/large",
        downloads = "50",
        likes = "10",
        comments = "5",
        userId = 12322,
        user = "johndoe",
        userImageURL = "https://example.com/user"
    )

    private val imageDetailsUi3 = ImageDetailsUiModel(
        id = 3,
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

    private val imageDetailsUi4 = ImageDetailsUiModel(
        id = 4,
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

    private val images = listOf(imageDetailsUi1, imageDetailsUi2, imageDetailsUi3, imageDetailsUi4)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testPixabayListScreenIfImagesAreEmptyAndShowNoDataMessage() {
        composeTestRule.apply {
            setListContent(emptyList())
            onNodeWithTag(IMAGE_LIST)
                .onChildren()
                .assertCountEquals(0)
            onAllNodes(hasText(activity.getString(R.string.oops)) and hasText(activity.getString(R.string.no_data_found)))
        }
    }

    @Test
    fun testPixabayListScreenIfImagesAreNotEmpty() {
        composeTestRule.apply {
            setListContent(images)
            onNodeWithTag(IMAGE_LIST)
                .onChildren()
                .assertCountEquals(4)
        }
    }

    @Test
    fun testImageItemDescription() {
        composeTestRule.apply {
            setListContent(images)
            images.forEachIndexed { index, item ->
                onNodeWithTag(IMAGE_LIST)
                    .onChildren()[index]
                    .assert(hasText(item.user))
            }
        }
    }

    @Test
    fun testCountryListItemClick() {
        val imageDetail = images.first()
        composeTestRule.apply {
            setItemContent(imageDetail)
            onNodeWithTag(ITEM_IMAGE_LIST)
                .assert(hasClickAction())
                .assert(hasText(imageDetail.user))
                .performClick()
        }
    }

    private fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.setItemContent(
        imageDetail: ImageDetailsUiModel
    ) {
        activity.runOnUiThread {
            activity.setContent { PaybackChallengeTheme { ItemPixabayList(imageDetail) {} } }
        }
    }

    private fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.setListContent(
        list: List<ImageDetailsUiModel>
    ) {
        activity.runOnUiThread {
            activity.setContent { PaybackChallengeTheme { PixabayList(Modifier, list) {} } }
        }
    }
}
