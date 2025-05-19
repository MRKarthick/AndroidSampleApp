package com.example.composejsonviewer.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.edgecasedesigns.androidsampleapp.ui.components.LoadingScreen
import com.edgecasedesigns.androidsampleapp.ui.components.itemsWithDividers
import com.example.composejsonviewer.data.model.Item

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = viewModel()) {
    val items = viewModel.items
    val isLoading = viewModel.isLoading

    if (isLoading) {
        LoadingScreen()
    } else {
        LazyColumn {
            itemsWithDividers(items) { item ->
                ListItem(item = item) {
                    navController.navigate("detail/${item.id}")
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