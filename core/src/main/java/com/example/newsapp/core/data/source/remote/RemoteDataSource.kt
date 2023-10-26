package com.example.newsapp.core.data.source.remote

import android.util.Log
import com.example.newsapp.core.BuildConfig
import com.example.newsapp.core.data.source.remote.network.ApiResponse
import com.example.newsapp.core.data.source.remote.network.ApiService
import com.example.newsapp.core.data.source.remote.response.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource (private val apiService: ApiService) {

    suspend fun getAllNews(): Flow<ApiResponse<List<NewsResponse>>> {
        //get data from remote api
        return flow {
            try {
                val apiKey = BuildConfig.API_KEY
                val response = apiService.getList(COUNTRY,apiKey)
                val dataArray = response.articles
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.articles))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object{
        private const val COUNTRY = "us"
    }
}