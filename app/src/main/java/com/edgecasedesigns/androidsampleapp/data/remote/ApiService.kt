package com.example.composejsonviewer.data.remote

import com.example.composejsonviewer.data.model.Item
import retrofit2.http.GET

interface ApiService {
    @GET("catchnz/android-test/master/data/data.json")
    suspend fun fetchItems(): List<Item>
}