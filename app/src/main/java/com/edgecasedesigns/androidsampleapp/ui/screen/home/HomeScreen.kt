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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.edgecasedesigns.androidsampleapp.ui.components.LoadingIndicator
import com.edgecasedesigns.androidsampleapp.ui.components.itemsWithDividers
import com.edgecasedesigns.androidsampleapp.data.model.Item
import com.edgecasedesigns.androidsampleapp.ui.components.LoadingScreen
import kotlinx.coroutines.launch

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