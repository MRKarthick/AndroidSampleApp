package com.example.composejsonviewer.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composejsonviewer.data.model.Item
import com.example.composejsonviewer.data.remote.Repository
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
