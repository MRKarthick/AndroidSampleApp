package com.edgecasedesigns.androidsampleapp.data.remote

import com.edgecasedesigns.androidsampleapp.data.model.Item

open class Repository : ItemRepository {
    private val api = NetworkModule.api

    override suspend fun getItems(): List<Item> = api.fetchItems()
}

interface ItemRepository {
    suspend fun getItems(): List<Item>
}