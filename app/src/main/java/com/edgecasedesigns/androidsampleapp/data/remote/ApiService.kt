package com.edgecasedesigns.androidsampleapp.data.remote

import com.edgecasedesigns.androidsampleapp.data.model.Item
import retrofit2.http.GET

interface ApiService {
    @GET("catchnz/android-test/master/data/data.json")
    suspend fun fetchItems(): List<Item>
}