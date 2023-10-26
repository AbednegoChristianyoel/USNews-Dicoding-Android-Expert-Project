package com.example.usnews.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.newsapp.core.domain.usecase.NewsUseCase

class HomeViewModel(newsUseCase: NewsUseCase) : ViewModel() {
    val news = newsUseCase.getAllNews().asLiveData()
}