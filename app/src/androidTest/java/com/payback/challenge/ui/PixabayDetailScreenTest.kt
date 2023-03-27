package com.payback.challenge.ui

import android.content.Context
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertWidthIsAtLeast
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.payback.challenge.MainActivity
import com.payback.challenge.R
import com.payback.challenge.features.pixabay.ui.model.ImageDetailsUiModel
import com.payback.challenge.features.pixabay.ui.pixabaydetail.DrawableId
import com.payback.challenge.features.pixabay.ui.pixabaydetail.PixabayDetailScreen
import com.payback.challenge.features.pixabay.ui.theme.PaybackChallengeTheme
import com.payback.challenge.utils.TestTags.DETAIL_SCREEN_COMMENT
import com.payback.challenge.utils.TestTags.DETAIL_SCREEN_DOWNLOAD
import com.payback.challenge.utils.TestTags.DETAIL_SCREEN_LARGE_IMAGE
import com.payback.challenge.utils.TestTags.DETAIL_SCREEN_LIKE
import com.payback.challenge.utils.TestTags.DETAIL_SCREEN_SOURCE_CREDIT
import com.payback.challenge.utils.TestTags.DETAIL_SCREEN_TAGS
import com.payback.challenge.utils.TestTags.PIXABAY_USERNAME
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalLayoutApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@RunWith(AndroidJUnit4::class)
class PixabayDetailScreenTest {

    private val context: Context = ApplicationProvider.getApplicationContext()

    private val imageDetailsUi = ImageDetailsUiModel(
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

    @ExperimentalMaterialApi
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testPixabayUserNameIsDisplayed() {
        composeTestRule.apply {
            setDetailContent()
            onNodeWithTag(PIXABAY_USERNAME).assertIsDisplayed()
                .assert(hasText(imageDetailsUi.user))
        }
    }

    @Test
    fun testLargeImageIsDisplayed() {
        composeTestRule.apply {
            setDetailContent()
            onNodeWithTag(DETAIL_SCREEN_LARGE_IMAGE).assertIsDisplayed()
                .assertHeightIsAtLeast(150.dp).assertWidthIsAtLeast(150.dp)
        }
    }

    @Test
    fun testCreditSourceIsDisplayed() {
        composeTestRule.apply {
            setDetailContent()
            onNodeWithTag(DETAIL_SCREEN_SOURCE_CREDIT).assertIsDisplayed()
                .assert(
                    hasText(
                        context.getString(R.string.source)
                            .plus(context.getString(R.string.source_url))
                    )
                )
                .assertIsEnabled()
        }
    }

    @Test
    fun testTagsAreDisplayed() {
        composeTestRule.apply {
            setDetailContent()
            imageDetailsUi.tags.forEach { tag ->
                onNodeWithTag(DETAIL_SCREEN_TAGS).assertIsDisplayed().onChildren()
                    .assertAny(hasText(tag)).assertCountEquals(imageDetailsUi.tags.size)
            }
        }
    }

    @Test
    fun testDownloadsAreDisplayed() {
        composeTestRule.apply {
            setDetailContent()
            onNodeWithTag(DETAIL_SCREEN_DOWNLOAD).assertIsDisplayed().onChildren()
                .assertAny(hasText(imageDetailsUi.downloads))
                .assertAny(hasDrawable(R.drawable.ic_download_24))
        }
    }

    @Test
    fun testLikesAreDisplayed() {
        composeTestRule.apply {
            setDetailContent()
            onNodeWithTag(DETAIL_SCREEN_LIKE).assertIsDisplayed().onChildren()
                .assertAny(hasText(imageDetailsUi.likes))
                .assertAny(hasDrawable(R.drawable.ic_like_24))
        }
    }

    @Test
    fun testCommentsAreDisplayed() {
        composeTestRule.apply {
            setDetailContent()
            onNodeWithTag(DETAIL_SCREEN_COMMENT).assertIsDisplayed().onChildren()
                .assertAny(hasText(imageDetailsUi.comments))
                .assertAny(hasDrawable(R.drawable.ic_comment_24))
        }
    }

    private fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.setDetailContent() {
        activity.runOnUiThread {
            activity.setContent {
                PaybackChallengeTheme {
                    PixabayDetailScreen(imageDetailsUi, rememberNavController())
                }
            }
        }
    }

    private fun hasDrawable(@DrawableRes id: Int): SemanticsMatcher =
        SemanticsMatcher.expectValue(DrawableId, id)
}
