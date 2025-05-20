package com.edgecasedesigns.androidsampleapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edgecasedesigns.androidsampleapp.data.model.Item
import com.edgecasedesigns.androidsampleapp.data.remote.ItemRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import com.edgecasedesigns.androidsampleapp.data.remote.Repository
import kotlinx.coroutines.delay

open class HomeViewModel(private val repository: ItemRepository = Repository()) : ViewModel() {
    var items by mutableStateOf<List<Item>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var isRefreshing by mutableStateOf(false)
        private set

    init {
        isLoading = true
        loadItems()
    }

    fun refresh() {
        isRefreshing = true
        loadItems()
    }

    // Adding delay for testing purposes. can set to 3000 or something to see the loading screen for brief 3 seconds
    private fun loadItems(delayTime: Long = 0) {
        viewModelScope.launch {
            try {
                delay(delayTime)
                items = repository.getItems()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
                isRefreshing = false
            }
        }
    }

    // I have Added this helper method for preview and testing
    internal fun setPreviewData(
        items: List<Item>,
        isLoading: Boolean = false,
        isRefreshing: Boolean = false
    ) {
        this.items = items
        this.isLoading = isLoading
        this.isRefreshing = isRefreshing
    }
}
