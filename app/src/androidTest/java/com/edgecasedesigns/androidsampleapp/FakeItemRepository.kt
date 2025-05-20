package com.edgecasedesigns.androidsampleapp

import com.edgecasedesigns.androidsampleapp.data.model.Item
import com.edgecasedesigns.androidsampleapp.data.remote.ItemRepository

class FakeItemRepository : ItemRepository {
    private val fakeItems = listOf(
        Item(1, "Test Title 1", "Test Subtitle 1", "Test Content 1"),
        Item(2, "Test Title 2", "Test Subtitle 2", "Test Content 2")
    )

    override suspend fun getItems(): List<Item> = fakeItems
}