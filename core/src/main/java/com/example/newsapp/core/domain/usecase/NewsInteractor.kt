package com.example.newsapp.core.domain.usecase

import com.example.newsapp.core.domain.model.News
import com.example.newsapp.core.domain.repository.INewsRepository

class NewsInteractor(private val newsRepository: INewsRepository): NewsUseCase {
    override fun getAllNews() = newsRepository.getAllNews()

    override fun getFavoriteNews() = newsRepository.getFavoriteNews()

    override fun setFavoriteNews(news: News, state: Boolean) = newsRepository.setFavoriteNews(news, state)
}
