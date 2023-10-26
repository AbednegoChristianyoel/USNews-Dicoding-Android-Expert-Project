package com.example.newsapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.newsapp.core.domain.usecase.NewsUseCase

class FavoriteViewModel(newsUseCase: NewsUseCase) : ViewModel() {
    val favoriteNews = newsUseCase.getFavoriteNews().asLiveData()
}