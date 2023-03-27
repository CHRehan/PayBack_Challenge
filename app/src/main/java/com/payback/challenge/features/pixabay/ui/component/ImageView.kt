package com.payback.challenge.features.pixabay.ui.component

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.imageLoader
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation

@Composable
fun ImageView(
    context: Context,
    imagePath: String,
    isCrossFadeEnable: Boolean,
    modifier: Modifier
) {
    val imageRequest = ImageRequest.Builder(context)
        .data(imagePath)
        .size(coil.size.Size.ORIGINAL)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .diskCachePolicy(CachePolicy.ENABLED)

    if (isCrossFadeEnable) {
        imageRequest.crossfade(isCrossFadeEnable)
        imageRequest.transformations(CircleCropTransformation())
    }

    val painter = rememberAsyncImagePainter(imageRequest.build(), context.imageLoader)

    if (painter.state is AsyncImagePainter.State.Loading) {
        CircularProgressIndicator(
            modifier = Modifier.wrapContentSize(),
            color = MaterialTheme.colors.onBackground
        )
    } else {
        Image(
            painter = painter,
            contentDescription = imagePath,
            modifier = modifier,
            contentScale = ContentScale.FillBounds
        )
    }
}
