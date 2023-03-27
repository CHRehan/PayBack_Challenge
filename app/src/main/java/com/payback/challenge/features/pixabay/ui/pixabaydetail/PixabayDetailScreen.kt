package com.payback.challenge.features.pixabay.ui.pixabaydetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.payback.challenge.R
import com.payback.challenge.features.common.openBrowser
import com.payback.challenge.features.pixabay.ui.component.ImageView
import com.payback.challenge.features.pixabay.ui.model.ImageDetailsUiModel
import com.payback.challenge.features.pixabay.ui.pixabaylist.TagRow
import com.payback.challenge.utils.TestTags.DETAIL_SCREEN_COMMENT
import com.payback.challenge.utils.TestTags.DETAIL_SCREEN_DOWNLOAD
import com.payback.challenge.utils.TestTags.DETAIL_SCREEN_LARGE_IMAGE
import com.payback.challenge.utils.TestTags.DETAIL_SCREEN_LIKE
import com.payback.challenge.utils.TestTags.DETAIL_SCREEN_SOURCE_CREDIT
import com.payback.challenge.utils.TestTags.IMAGE_DETAIL
import com.payback.challenge.utils.TestTags.PIXABAY_USERNAME

val DrawableId = SemanticsPropertyKey<Int>("DrawableResId")
var SemanticsPropertyReceiver.drawableId by DrawableId

@ExperimentalLayoutApi
@Composable
fun PixabayDetailScreen(
    imageDetail: ImageDetailsUiModel,
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.testTag(PIXABAY_USERNAME),
                        text = imageDetail.user
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.primary

            )
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues).testTag(IMAGE_DETAIL)) {
            item {
                LargeImageCard(imageDetail.largeImageURL)

                FlowRow(
                    modifier = Modifier.fillMaxWidth().padding(paddingValues),
                    mainAxisAlignment = FlowMainAxisAlignment.Center,
                    mainAxisSpacing = 10.dp,
                    crossAxisSpacing = 10.dp
                ) {
                    IconTextStack(
                        modifier = Modifier.testTag(DETAIL_SCREEN_DOWNLOAD),
                        icon = R.drawable.ic_download_24,
                        imageDetail.downloads
                    )
                    IconTextStack(
                        modifier = Modifier.testTag(DETAIL_SCREEN_COMMENT),
                        icon = R.drawable.ic_comment_24,
                        imageDetail.comments
                    )
                    IconTextStack(
                        modifier = Modifier.testTag(DETAIL_SCREEN_LIKE),
                        icon = R.drawable.ic_like_24,
                        imageDetail.likes
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                TagRow(
                    modifier = Modifier.fillMaxWidth(),
                    tags = imageDetail.tags
                )
                Spacer(modifier = Modifier.height(20.dp))
                SourceCreditView(modifier = Modifier.padding(horizontal = 16.dp))
            }
        }
    }
}

@Composable
fun IconTextStack(
    modifier: Modifier,
    icon: Int,
    text: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painterResource(id = icon),
            contentDescription = text,
            modifier = Modifier
                .size(54.dp)
                .padding(12.dp)
                .semantics { drawableId = icon },
            tint = MaterialTheme.colors.onBackground
        )
        Text(
            text = text,
            fontSize = 18.sp,
            style = MaterialTheme.typography.overline,
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Composable
private fun LargeImageCard(imagePath: String) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp)
            .testTag(DETAIL_SCREEN_LARGE_IMAGE),
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.background,
        elevation = 6.dp
    ) {
        ImageView(context, imagePath, false, Modifier)
    }
}

@Composable
private fun SourceCreditView(modifier: Modifier) {
    val context = LocalContext.current
    val annotatedString = buildAnnotatedString {
        append(stringResource(R.string.source))
        pushStringAnnotation(
            tag = stringResource(R.string.source),
            annotation = stringResource(R.string.source_url)
        )
        withStyle(style = SpanStyle(color = MaterialTheme.colors.onBackground)) {
            append(stringResource(R.string.source_url))
        }
    }
    ClickableText(
        modifier = modifier.testTag(DETAIL_SCREEN_SOURCE_CREDIT),
        text = annotatedString,
        style = TextStyle(
            color = MaterialTheme.colors.onBackground
        ),
        onClick = { offset ->
            annotatedString.getStringAnnotations(
                tag = context.getString(R.string.source),
                start = offset,
                end = offset
            ).firstOrNull()?.let { url ->
                context.openBrowser(url.item)
            }
        }

    )
}
