package com.example.newsapp.core.data

import com.example.newsapp.core.data.source.local.LocalDataSource
import com.example.newsapp.core.data.source.remote.RemoteDataSource
import com.example.newsapp.core.data.source.remote.network.ApiResponse
import com.example.newsapp.core.data.source.remote.response.NewsResponse
import com.example.newsapp.core.domain.model.News
import com.example.newsapp.core.domain.repository.INewsRepository
import com.example.newsapp.core.utils.AppExecutors
import com.example.newsapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): INewsRepository {

    override fun getAllNews(): Flow<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<NewsResponse>>() {
            override fun loadFromDB(): Flow<List<News>> {
                return localDataSource.getAllNews().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<News>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<NewsResponse>>> =
                remoteDataSource.getAllNews()

            override suspend fun saveCallResult(data: List<NewsResponse>) {
                val newsList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertNews(newsList)
            }
        }.asFlow()

    override fun getFavoriteNews(): Flow<List<News>> {
        return localDataSource.getFavoriteNews().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteNews(news: News, state: Boolean) {
        val newsEntity = DataMapper.mapDomainToEntity(news)
        appExecutors.diskIO().execute {
            if (newsEntity != null) {
                localDataSource.setFavoriteNews(newsEntity, state)
            }
        }
    }
}