package com.example.newsapp.webview

import androidx.lifecycle.ViewModel
import com.example.newsapp.core.domain.model.News
import com.example.newsapp.core.domain.usecase.NewsUseCase

class WebViewViewModel (private val newsUseCase: NewsUseCase) : ViewModel() {
    fun setFavoriteNews(news: News, newStatus:Boolean) =
        newsUseCase.setFavoriteNews(news, newStatus)
}