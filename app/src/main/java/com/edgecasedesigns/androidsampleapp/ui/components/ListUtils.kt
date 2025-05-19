package com.edgecasedesigns.androidsampleapp.ui.components

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider

fun <T> LazyListScope.itemsWithDividers(
    items: List<T>,
    itemContent: @Composable (T) -> Unit
) {
    itemsIndexed(items) { index, item ->
        itemContent(item)

        if (index < items.lastIndex) {
            HorizontalDivider(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                thickness = 0.5.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
            )
        }
    }
}