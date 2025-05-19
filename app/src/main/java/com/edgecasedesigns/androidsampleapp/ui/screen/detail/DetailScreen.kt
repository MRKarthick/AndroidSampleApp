package com.example.composejsonviewer.ui.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composejsonviewer.ui.screen.home.HomeViewModel

@Composable
fun DetailScreen(itemId: Int, viewModel: HomeViewModel = viewModel()) {
    val item = viewModel.items.find { it.id == itemId }

    item?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = it.title, style = MaterialTheme.typography.headlineSmall)
            Text(text = it.subtitle, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = it.content, style = MaterialTheme.typography.bodyLarge)
        }
    }
}