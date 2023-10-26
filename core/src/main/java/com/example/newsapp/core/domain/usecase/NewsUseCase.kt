package com.example.newsapp.core.domain.usecase

import com.example.newsapp.core.data.Resource
import com.example.newsapp.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    fun getAllNews(): Flow<Resource<List<News>>>
    fun getFavoriteNews(): Flow<List<News>>
    fun setFavoriteNews(news: News, state: Boolean)
}