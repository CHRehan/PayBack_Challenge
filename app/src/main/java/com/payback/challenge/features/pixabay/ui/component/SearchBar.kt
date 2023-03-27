package com.payback.challenge.features.pixabay.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import com.payback.challenge.R

@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    onSearch: (searchQuery: String) -> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        trailingIcon = {
            Image(
                painter = painterResource(R.drawable.baseline_search_24),
                contentScale = ContentScale.FillBounds,
                contentDescription = "",
                modifier = Modifier.clickable {
                    onSearch(value)
                },
                colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onBackground)
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(value)
            }
        )

    )
}
