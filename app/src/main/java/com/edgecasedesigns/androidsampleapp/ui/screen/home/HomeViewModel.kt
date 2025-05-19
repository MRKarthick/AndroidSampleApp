package com.edgecasedesigns.androidsampleapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edgecasedesigns.androidsampleapp.data.model.Item
import com.edgecasedesigns.androidsampleapp.data.remote.Repository
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import kotlinx.coroutines.delay

class HomeViewModel : ViewModel() {
    private val repository = Repository()

    var items by mutableStateOf<List<Item>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var isRefreshing by mutableStateOf(false)    // for pull-to-refresh
        private set

    init {
        isLoading = true
        loadItems(2000)
    }

    fun refresh() {
        isRefreshing = true
        loadItems(2000)
    }

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
}
