package com.edgecasedesigns.androidsampleapp.ui.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.edgecasedesigns.androidsampleapp.ui.screen.home.HomeViewModel
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.edgecasedesigns.androidsampleapp.data.model.Item

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    itemId: Int,
    viewModel: HomeViewModel = viewModel()
) {
    val item = viewModel.items.find { it.id == itemId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = item?.title ?: "Detail") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        item?.let {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Text(text = it.content, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    val navController = rememberNavController()
    val previewViewModel = HomeViewModel().apply {
        setPreviewData(
            items = listOf(
                Item(
                    id = 1,
                    title = "Sample Title 1",
                    subtitle = "Sample Subtitle 1",
                    content = "This is the detailed content of item 1 for preview."
                ),
                Item(
                    id = 2,
                    title = "Sample Title 2",
                    subtitle = "Sample Subtitle 2",
                    content = "This is the detailed content of item 2 for preview."
                )
            ),
            isLoading = false,
            isRefreshing = false
        )
    }

    DetailScreen(
        navController = navController,
        itemId = 1,
        viewModel = previewViewModel
    )
}