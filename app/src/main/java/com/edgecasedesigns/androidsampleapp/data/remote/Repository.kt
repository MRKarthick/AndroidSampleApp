package com.edgecasedesigns.androidsampleapp.data.remote

class Repository {
    private val api = NetworkModule.api

    suspend fun getItems() = api.fetchItems()
}