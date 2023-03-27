package com.payback.challenge.features.pixabay.ui.pixabaylist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.payback.challenge.features.pixabay.ui.component.ImageView
import com.payback.challenge.features.pixabay.ui.model.ImageDetailsUiModel
import com.payback.challenge.utils.TestTags.ITEM_IMAGE_LIST

@ExperimentalLayoutApi
@ExperimentalMaterialApi
@Composable
fun ItemPixabayList(imageDetailUi: ImageDetailsUiModel, onClick: () -> Unit) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 8.dp)
            .testTag(ITEM_IMAGE_LIST)
            .clickable { onClick.invoke() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        ImageView(context, imageDetailUi.previewURL, true, Modifier.size(60.dp))
        Column(Modifier.padding(8.dp)) {
            Text(
                text = imageDetailUi.user,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onBackground
            )
            TagRow(
                modifier = Modifier.padding(top = 2.dp),
                tags = imageDetailUi.tags
            )
        }
    }
}
