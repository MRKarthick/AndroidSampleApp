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

    var isLoading by mutableStateOf(true)
        private set

    init {
        loadItems()
    }

    fun refresh() {
        loadItems()
    }

    private fun loadItems() {
        isLoading = true
        viewModelScope.launch {
            try {
                delay(2000) // I have delay for demoing the loading screen.
                items = repository.getItems()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }
}
