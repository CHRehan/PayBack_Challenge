package com.payback.challenge.features.pixabay.ui.pixabaylist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.payback.challenge.features.pixabay.ui.model.ImageDetailsUiModel
import com.payback.challenge.utils.TestTags.IMAGE_LIST

@ExperimentalLayoutApi
@ExperimentalMaterialApi
@Composable
fun PixabayList(
    modifier: Modifier,
    list: List<ImageDetailsUiModel>,
    onCountryClick: (ImageDetailsUiModel) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.testTag(IMAGE_LIST)
    ) {
        items(list.size, { itemKey ->
            itemKey.toString()
        }, itemContent = { itemIndex ->
                ItemPixabayList(imageDetailUi = list[itemIndex]) {
                    onCountryClick(list[itemIndex])
                }
            })
    }
}
