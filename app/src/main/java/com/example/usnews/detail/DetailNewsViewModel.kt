package com.example.usnews.detail

import androidx.lifecycle.ViewModel
import com.example.newsapp.core.domain.model.News
import com.example.newsapp.core.domain.usecase.NewsUseCase

class DetailNewsViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {
    fun setFavoriteNews(news: News, newStatus:Boolean) =
        newsUseCase.setFavoriteNews(news, newStatus)
}