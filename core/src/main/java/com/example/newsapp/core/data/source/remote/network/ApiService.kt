package com.example.newsapp.core.data.source.remote.network

import com.example.newsapp.core.data.source.remote.response.ListNewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getList(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): ListNewsResponse
}