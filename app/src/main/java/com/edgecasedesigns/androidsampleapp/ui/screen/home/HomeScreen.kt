@file:OptIn(ExperimentalMaterial3Api::class)

package com.edgecasedesigns.androidsampleapp.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.edgecasedesigns.androidsampleapp.ui.components.LoadingIndicator
import com.edgecasedesigns.androidsampleapp.ui.components.itemsWithDividers
import com.edgecasedesigns.androidsampleapp.data.model.Item
import com.edgecasedesigns.androidsampleapp.data.remote.ItemRepository
import com.edgecasedesigns.androidsampleapp.ui.components.LoadingScreen
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = viewModel()) {
    val items = viewModel.items
    val isLoading = viewModel.isLoading
    val isRefreshing = viewModel.isRefreshing
    val coroutineScope = rememberCoroutineScope()
    val pullToRefreshState = rememberPullToRefreshState()

    val onRefresh: () -> Unit = {
        coroutineScope.launch {
            viewModel.refresh()
        }
    }

    if (isLoading && items.isEmpty()) {
        LoadingScreen()
    } else {
        PullToRefreshBox(
            modifier = Modifier.fillMaxSize(),
            state = pullToRefreshState,
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
            indicator = {
                if (isRefreshing) {
                    LoadingIndicator()
                }
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                itemsWithDividers(items) { item ->
                    ListItem(item = item) {
                        navController.navigate("detail/${item.id}")
                    }
                }
            }
        }
    }
}

@Composable
fun ListItem(item: Item, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = item.title, style = MaterialTheme.typography.bodyLarge)
        Row {
            Text(text = item.subtitle, style = MaterialTheme.typography.bodyMedium)
            Icon(Icons.Default.ChevronRight, contentDescription = "Navigate")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()

    // Fake repository for preview data
    val fakeRepository = object : ItemRepository {
        override suspend fun getItems(): List<Item> = listOf(
            Item(1, "Sample Title 1", "Sample Subtitle 1", "Sample Content 1"),
            Item(2, "Sample Title 2", "Sample Subtitle 2", "Sample Content 2")
        )
    }

    // Creating my ViewModel instance manually as preview is not support lifecycle
    val previewViewModel = HomeViewModel(fakeRepository).apply {
        setPreviewData(
            items = runBlocking { fakeRepository.getItems() },
            isLoading = false,
            isRefreshing = false
        )
    }

    HomeScreen(navController = navController, viewModel = previewViewModel)
}