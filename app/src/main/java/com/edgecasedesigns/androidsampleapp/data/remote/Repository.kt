package com.edgecasedesigns.androidsampleapp.data.remote

open class Repository {
    private val api = NetworkModule.api

    open suspend fun getItems() = api.fetchItems()
}